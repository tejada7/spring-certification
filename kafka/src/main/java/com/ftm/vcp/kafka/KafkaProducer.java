package com.ftm.vcp.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message, String topic) {
        log.info("Producing message: {}", message);
        kafkaTemplate.send(topic, "key", message)
                     .whenComplete((result, ex) -> {
                         if (ex == null) {
                             log.info("Message sent to topic: {}", message);
                         } else {
                             log.error("Failed to send message", ex);
                         }
                     });
    }
}
