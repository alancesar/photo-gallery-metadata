package org.alancesar.metadata.messaging;

import org.alancesar.metadata.metadata.Metadata;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Producer {
    public static final String EVENT_TYPE = "METADATA";
    public static final String EVENT_TYPE_KEY = "event-type";

    @Value("${rabbitmq.worker-exchange-name}")
    private String workerExchangeName;

    private final RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produce(String filename, Map<String, String> exif) {
        rabbitTemplate.convertAndSend(workerExchangeName, "", new Message(filename, exif), m -> {
            m.getMessageProperties().getHeaders().put(EVENT_TYPE_KEY, EVENT_TYPE);
            return m;
        });
    }
}
