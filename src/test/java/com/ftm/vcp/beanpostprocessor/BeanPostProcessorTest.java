package com.ftm.vcp.beanpostprocessor;

import com.ftm.vcp.beanpostprocessor.model.Foo;
import com.ftm.vcp.beanpostprocessor.config.BeanPostProcessorConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DisplayNameGenerator.IndicativeSentences;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@DisplayName("A class implementing BeanPostProcessor")
@DisplayNameGeneration(IndicativeSentences.class)
@IndicativeSentencesGeneration(separator = " ", generator = ReplaceUnderscores.class)
@ExtendWith(OutputCaptureExtension.class)
class BeanPostProcessorTest {

    @Test
    void should_call_post_process_methods(final CapturedOutput output) {
        // Given
        final var applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

        // When
        applicationContext.getBean(Foo.class);

        // Then
        then(output.toString()).contains(
                "Calling postProcessBeforeInitialization...",
                "Calling postProcessAfterInitialization..."
        );
    }

}
