package com.ftm.vcp.beaninitialization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;

@ExtendWith(OutputCaptureExtension.class)
class BeanInitializationTest {

    @Test
    void should_print_messages_in_correct_order(final CapturedOutput output) {
        // Given
        final var context = new AnnotationConfigApplicationContext(BeanInitializationConfig.class);
        final var bean = context.getBean(ABean.class);

        // When
        context.close();

        // Then
        thenSoftly(softly -> {
            softly.then(bean).isNotNull();
            softly.then(output.toString())
                    .containsSequence("""
                                              1. Calling aBean's constructor.
                                              2. Invoking @PostContruct method.
                                              3. Invoking InitializingBean's afterPropertiesSet method.
                                              4. Invoking customInit method.
                                              5. Invoking @PreDestroy method.
                                              6. Invoking DisposableBean's destroy method.
                                              7. Invoking customDestroy method.
                                              """
                    );
        });
    }

    @Test
    void should_print_messages_in_correct_order_static_initialization(final CapturedOutput output) {
        // Given
        final var context = new AnnotationConfigApplicationContext(BeanInitializationStaticConfig.class);
        final var bean = context.getBean(ABeanWithPostProcessors.class);

        // When
        context.close();

        // Then
        thenSoftly(softly -> {
            softly.then(bean).isNotNull();
            softly.then(output.toString())
                    .containsSequence("""
                                              1. Calling aBean's constructor.
                                              2. Invoking InitializingBean's afterPropertiesSet method.
                                              3. Invoking customInit method.
                                              4. Calling post process bean factory...
                                              5. Calling postProcessBeforeInitialization...
                                              6. Calling postProcessAfterInitialization...
                                              7. Invoking DisposableBean's destroy method.
                                              8. Invoking customDestroy method.
                                              """
                    );
        });
    }
}
