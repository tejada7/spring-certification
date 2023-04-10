package com.ftm.vcp.callback;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;

@ExtendWith(OutputCaptureExtension.class)
class CallbackTest {

    @Test
    void should_print_messages_in_correct_order(final CapturedOutput output) {
        // Given
        final var context = new AnnotationConfigApplicationContext(CallbackConfig.class);
        final var bean = context.getBean(ABean.class);

        // When
        context.close();

        // Then
        thenSoftly(softly -> {
            softly.then(bean).isNotNull();
            softly.then(output.toString()).containsSequence("""
                                                                    Calling aBean's constructor. 
                                                                    Invoking @PostContruct method.
                                                                    Invoking DisposableBean's afterPropertiesSet method.
                                                                    Invoking customInit method.
                                                                    Invoking @PreDestroy method.
                                                                    Invoking DisposableBean's destroy method.
                                                                    Invoking customDestroy method.
                                                                    """
            );
        });
    }
}
