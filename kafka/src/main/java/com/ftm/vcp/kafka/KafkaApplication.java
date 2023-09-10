package com.ftm.vcp.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @RestController
    static class Controller {

        private final KafkaProducer producer;

        Controller(final KafkaProducer producer) {
            this.producer = producer;
        }

        @PostMapping("/push-message")
        public void publishMessage(@RequestBody String message) {
            producer.sendMessage(message, KafkaConsumer.TOPIC);
        }
    }
}
