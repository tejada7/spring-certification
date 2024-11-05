package com.ftm.vcp;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@DisplayNameGeneration(ReplaceUnderscores.class)
@Execution(value = ExecutionMode.CONCURRENT, reason = "learning how to run concurrent tests")
// don't forget to add the vm option -Djunit.jupiter.execution.parallel.enabled=true
class ParallelTest {

    @Test
    void test_decimal() {
        await().pollDelay(ofSeconds(1))
                .untilAsserted(() -> assertThat(10 + 10).isEqualTo(20));
    }

    @Test
    void test_binary() {
        await().pollDelay(ofSeconds(1))
                .untilAsserted(() -> assertThat(0b10 + 0b10).isEqualTo(4));
    }

    @Test
    void test_octal() {
        await().pollDelay(ofSeconds(1))
                .untilAsserted(() -> assertThat(010 + 010).isEqualTo(16));
    }

    @Test
    void test_hexadecimal() {
        await().pollDelay(ofSeconds(1))
                .untilAsserted(() -> assertThat(0x10 + 0x10).isEqualTo(32));
    }
}
