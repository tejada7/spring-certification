package com.ftm.vcp.aop;

import com.ftm.vcp.aop.config.AspectJConfig;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@SpringJUnitConfig(AspectJConfig.class)
@ExtendWith(OutputCaptureExtension.class)
@DisplayNameGeneration(ReplaceUnderscores.class)
class AspectTest {

    @Autowired
    ApplicationContext context;

    @Test
    void should_create_aspectj_proxy(final CapturedOutput output) {
        // Given
        final var printable = context.getBean(Printable.class);

        // When
        printable.printFullName();

        // Then
        thenSoftly(soft -> {
            soft.then(output)
                    .containsSequence("""
                                              Printed by an advice, within the method printFullName.
                                              Full name: John Doe        
                                              """);
            soft.then(printable)
                    .isNotExactlyInstanceOf(Printable.class)
                    .isNotExactlyInstanceOf(Person.class);
        });
    }
}
