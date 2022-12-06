package com.ftm.vcp.events;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DisplayNameGenerator.IndicativeSentences;

@DisplayName("An event")
@DisplayNameGeneration(IndicativeSentences.class)
@IndicativeSentencesGeneration(separator = " ", generator = ReplaceUnderscores.class)
@SpringJUnitConfig(EventConfig.class)
@ExtendWith(OutputCaptureExtension.class)
class EventTest {

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    EventTest(final ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Test
    void should_be_consumed_on_messages_returning_should_be_consumed_to_true(final CapturedOutput log) throws InterruptedException {
        // Given
        final var aMessageToBeConsumed = new Message("1", true);

        // When
        eventPublisher.publishEvent(aMessageToBeConsumed);
        Thread.sleep(1000);

        // Then
        then(log.toString()).contains("consuming message 1");
    }

    @Test
    void should_not_consume_messages_returning_should_be_consumed_to_false(final CapturedOutput log) throws InterruptedException {
        // Given
        final var aMessageNotToBeConsumed = new Message("1", false);

        // When
        eventPublisher.publishEvent(aMessageNotToBeConsumed);
        Thread.sleep(1000);

        // Then
        then(log.toString()).doesNotContain("consuming message 1");
    }
}
