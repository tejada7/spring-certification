package com.ftm.vcp.bootexamples.testutils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(JsonAssertExtension.class)
class TestCustomExtension {

    @Test
    void should_print_log(JsonAssert jsonAssert) {
        final var aService = new MyService();

        aService.foo();

        jsonAssert
            .hasAnyInfoLog()
            .verify();
    }

    static class MyService {

        private static final Logger log = LoggerFactory.getLogger(MyService.class);

        public void foo() {
            log.info("foo");
        }
    }
}
