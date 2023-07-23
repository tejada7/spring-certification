package com.ftm.vcp.bootexamples.infrastructure.driven.async;

import com.ftm.vcp.bootexamples.infrastructure.driven.config.AsyncConfig;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.Duration;

import static org.awaitility.Awaitility.await;

@SpringJUnitConfig({AsynchronousService.class, AsyncConfig.class})
@DisplayNameGeneration(ReplaceUnderscores.class)
@ExtendWith(OutputCaptureExtension.class)
class AsynchronousServiceTest {

    @Test
    void should_execute_method_in_a_different_thread(@Autowired AsynchronousService service, CapturedOutput output) {
        service.doSomethingMethod();
        await()
                .atMost(Duration.ofSeconds(2))
                .until(() -> output.toString()
                                   .contains("Doing something from an async service, in the thread customTaskExecutor"));
    }
}
