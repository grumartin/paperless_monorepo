package at.fhtw.swkom.paperless.services;

import at.fhtw.swkom.paperless.config.RabbitMQConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public interface OcrService {
    @RabbitListener(queues = RabbitMQConfig.OCR_OUT_QUEUE_NAME)
    void processMessage(String message) throws JsonProcessingException;
}
