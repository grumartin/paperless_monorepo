package at.fhtw.swkom.paperless;

import at.fhtw.swkom.paperless.config.RabbitMQConfig;
import at.fhtw.swkom.paperless.services.dto.Document;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rabbitmq.client.DeliverCallback;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TesseractOcrServiceTest {
    private Document doc;
    private ObjectMapper mapper;

    private String storagePath;


    @BeforeEach
    void setup() {
        doc = new Document();
        doc.setId(1);
        doc.setTitle( JsonNullable.of("Communication Patterns") );
        doc.setAdded(OffsetDateTime.now() );
        doc.setCreated(OffsetDateTime.now());
        doc.setArchivedFileName( JsonNullable.of("CommunicationPatterns.pdf") );
        doc.setOriginalFileName( JsonNullable.of("CommunicationPatterns.pdf"));

        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new JsonNullableModule());

        storagePath = "test/MasteringATopic.jpg";
    }

    @Test
    void ocrServiceTest() throws IOException, InterruptedException {
        RabbitMQConfig config = new RabbitMQConfig();
        final AtomicBoolean answerReceived = new AtomicBoolean(false);

        // init receiver (=queue message consumer)
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("Received: " + message);

            var result = mapper.readValue( message, Document.class );
            assertTrue( result.getContent().isPresent() );

            synchronized (answerReceived) {
                answerReceived.set(true);
                answerReceived.notifyAll();
            }
        };
        config.connectionFactory()
                .createConnection()
                .createChannel(false)
                .basicConsume(RabbitMQConfig.OCR_OUT_QUEUE_NAME, true, deliverCallback, consumerTag -> {});

        // do transmit
        //ocrService.processMessage( mapper.writeValueAsString(doc) ); // this would bypass the OCR_IN_QUEUE
        RabbitTemplate rabbitTemplate = config.rabbitTemplate();
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.OCR_IN_QUEUE_NAME, mapper.writeValueAsString(doc), message1 -> {
            message1.getMessageProperties().getHeaders().put(RabbitMQConfig.DOCUMENT_STORAGE_PATH_PROPERTY_NAME, storagePath);
            return message1;
        });

        synchronized ( answerReceived ) {
            answerReceived.wait(30000);
        }

        assertTrue( answerReceived.get() );
    }

}