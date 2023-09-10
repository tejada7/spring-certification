package com.ftm.vcp.kafka;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.time.Duration;
import java.util.UUID;

import static com.ftm.vcp.kafka.KafkaConsumer.TOPIC;
import static org.assertj.core.api.BDDAssertions.then;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@Testcontainers
@SpringBootTest
class KafkaApplicationTests {

    private static final File KAFKA_COMPOSE_FILE = new File("src/main/resources/docker-compose.yaml");
    private static final String KAFKA_SERVICE = "kafka";
    private static final int SSL_PORT = 9093;

    @Container
    DockerComposeContainer<?> container =
            new DockerComposeContainer<>(KAFKA_COMPOSE_FILE)
                    .withExposedService(KAFKA_SERVICE, SSL_PORT, Wait.forListeningPort());

    @DynamicPropertySource
    private static void registerAdditionalProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.producer.properties[transaction.timeout.ms]", () -> "120000");
        registry.add("spring.kafka.consumer.properties[transaction.timeout.ms]", () -> "120000");
    }

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaConsumer kafkaConsumer;

    @BeforeEach
    void debugEnvironment(@Autowired ConfigurableEnvironment environment) {
        environment.getPropertySources().forEach(propertySource -> {
            System.err.println(">>>>> " + propertySource.getName() + " :: " + propertySource.getSource());
        });
    }

    @Test
    void givenSslIsConfigured_whenProducerSendsMessageOverSsl_thenConsumerReceivesOverSsl() {
        final var message = generateSampleMessage();
        kafkaProducer.sendMessage(message, TOPIC);

        await().atMost(Duration.ofMinutes(2))
               .untilAsserted(() -> then(kafkaConsumer.messages).containsExactly(message));
    }

    private static String generateSampleMessage() {
        return UUID.randomUUID().toString();
    }
}
