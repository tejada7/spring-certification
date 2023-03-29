package com.ftm.vcp.beanfactorypostprocessor;

import com.ftm.vcp.beanfactorypostprocessor.config.BeanFactoryPostProcessorConfig;
import com.ftm.vcp.beanfactorypostprocessor.model.Foo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DisplayNameGenerator.IndicativeSentences;

@DisplayName("A class implementing BeanFactoryPostProcessor")
@DisplayNameGeneration(IndicativeSentences.class)
@IndicativeSentencesGeneration(separator = " ", generator = ReplaceUnderscores.class)
@ExtendWith(OutputCaptureExtension.class)
class BeanFactoryPostProcessorTest {

    @Test
    void should_call_post_process_bean_factory(final CapturedOutput output) {
        // Given
        final var applicationContext = new AnnotationConfigApplicationContext(BeanFactoryPostProcessorConfig.class);

        // When
        applicationContext.getBean(Foo.class);

        // Then
        then(output.toString()).contains("Calling post process bean factory...");
    }
}
