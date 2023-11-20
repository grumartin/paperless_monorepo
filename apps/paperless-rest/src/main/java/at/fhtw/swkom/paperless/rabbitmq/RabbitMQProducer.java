package at.fhtw.swkom.paperless.rabbitmq;

import at.fhtw.swkom.paperless.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String msg){
        try {
            this.rabbitTemplate.convertAndSend(RabbitMQConfig.OCR_OUT_QUEUE_NAME, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
