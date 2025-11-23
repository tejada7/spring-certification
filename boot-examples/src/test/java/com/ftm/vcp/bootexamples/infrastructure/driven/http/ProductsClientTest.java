package com.ftm.vcp.bootexamples.infrastructure.driven.http;

import com.ftm.vcp.bootexamples.infrastructure.driven.config.HttpClientConfig;
import org.assertj.core.api.BDDAssertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchRuntimeException;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;
import static org.awaitility.Awaitility.await;

@SpringJUnitConfig({HttpClientConfig.class})
@DisplayNameGeneration(ReplaceUnderscores.class)
@TestPropertySource(properties = "product.url=localhost:8888")
@ExtendWith(OutputCaptureExtension.class)
class ProductsClientTest {

    private final ProductsClient productsClient;

    @Autowired
    ProductsClientTest(final ProductsClient productsClient) {
        this.productsClient = productsClient;
    }

    @Test
    void should_exhaust_retries_limit(CapturedOutput output) {
        final var expectedRetryMessage = "Retrying...";

        final var throwable = catchRuntimeException(() -> productsClient.getProductById(1L));

        then(throwable).isInstanceOf(RuntimeException.class).hasMessage("Retries exhausted: 3/3");
        await().atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> then(output).contains(
                expectedRetryMessage, expectedRetryMessage, expectedRetryMessage)
            );
    }
}
