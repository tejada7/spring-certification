package com.ftm.vcp.bootexamples;

import com.ftm.vcp.bootexamples.application.CreatorApi;
import com.ftm.vcp.bootexamples.infrastructure.driven.config.CustomSettings;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.stream.IntStream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

// --add-opens java.base/java.lang=ALL-UNNAMED --add-modules spring.boot.devtools
@SpringBootApplication
@EnableConfigurationProperties(CustomSettings.class)
class BootExamplesApplication {

    public static void main(String[] args) {
        final var springApplication = new SpringApplication(BootExamplesApplication.class);
        // This allows to measure start-up time
        springApplication.setApplicationStartup(new BufferingApplicationStartup(1000));
        springApplication.run(args);
    }

    @Bean
    @Profile("dev")
    CommandLineRunner start(CreatorApi creator) {
        return args -> creator.create("foo4");
    }

    @Bean
    @Profile("dev")
    ApplicationRunner applicationRunner(CreatorApi creator) {
        return args -> creator.create("foo5");
    }

    CommandLineRunner initMockServer() {
        return args -> {
            final var wireMockServer = new WireMockServer(9001);
            wireMockServer.start();
            IntStream.range(1, 5).forEach(productId -> {
                try {
                    wireMockServer.stubFor(
                        get(urlPathEqualTo("/products/" + productId))
                            .willReturn(aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .withBody(Files.readString(ResourceUtils.getFile(
                                    "classpath:mocks/product_" + productId + ".json").toPath())))
                    );
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        };
    }
}
