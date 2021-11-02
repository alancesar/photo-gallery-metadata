package org.alancesar.metadata.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    @Value("${rabbitmq.worker-exchange-name}")
    private String workerExchangeName;

    private final RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produce(OutgoingMessage message) {
        rabbitTemplate.convertAndSend(workerExchangeName, "", message);
    }
}
