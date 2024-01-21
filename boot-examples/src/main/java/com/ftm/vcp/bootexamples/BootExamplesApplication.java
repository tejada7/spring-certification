package com.ftm.vcp.bootexamples;

import com.ftm.vcp.bootexamples.infrastructure.driven.config.CustomSettings;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.EncapsulatedFooRepository;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
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
public class BootExamplesApplication {

    public static void main(String[] args) {
        final var springApplication = new SpringApplication(BootExamplesApplication.class);
        // This allows to measure start-up time
        springApplication.setApplicationStartup(new BufferingApplicationStartup(1000));
        springApplication.run(args);
    }

    @Bean
    CommandLineRunner start(EncapsulatedFooRepository fooRepository) {
        return args -> fooRepository.create(new FooEntity(null, "foo4"));
    }

    @Bean
    ApplicationRunner applicationRunner(EncapsulatedFooRepository fooRepository) {
        return args -> fooRepository.create(new FooEntity(null, "foo5"));
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
