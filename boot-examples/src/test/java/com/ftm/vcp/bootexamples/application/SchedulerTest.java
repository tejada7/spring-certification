package com.ftm.vcp.bootexamples.application;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.Duration;

import static org.assertj.core.api.BDDAssertions.then;
import static org.awaitility.Awaitility.await;

@DisplayNameGeneration(ReplaceUnderscores.class)
@ExtendWith(OutputCaptureExtension.class)
@SpringJUnitConfig({Scheduler.class, SchedulerTest.ScheduledConfig.class})
class SchedulerTest {

    @Test
    void should_test_scheduler(CapturedOutput output) {
        await()
                .pollDelay(Duration.ofMillis(3000))
                .untilAsserted(() -> then(output.toString()).contains("Calling from scheduler"));
    }

    @TestConfiguration
    @EnableScheduling
    public static class ScheduledConfig {

    }
}
