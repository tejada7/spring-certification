package com.ftm.vcp.bootexamples;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import static org.assertj.core.api.BDDAssertions.catchRuntimeException;
import static org.assertj.core.api.BDDAssertions.then;

@DisplayNameGeneration(ReplaceUnderscores.class)
class BootWithoutAnnotationTest {

    @Test
    void should_start_boot_app_without_spring_boot_test_annotation() {
        final var properties = "--product.url=http://product-service";

        final var throwable = catchRuntimeException(() ->
                SpringApplication.from(BootExamplesApplication::main)
                        .withAdditionalProfiles("test")
                        .run(properties).getApplicationContext()
        );

        then(throwable).doesNotThrowAnyException();
    }
}
