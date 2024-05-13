package com.zing.ledger.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEvent(String topic, String payload) {
        kafkaTemplate.send(topic,payload);
    }
}

