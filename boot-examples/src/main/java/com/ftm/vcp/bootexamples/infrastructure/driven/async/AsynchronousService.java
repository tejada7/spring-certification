package com.ftm.vcp.bootexamples.infrastructure.driven.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class AsynchronousService {

    private final Logger log = Logger.getLogger(getClass().getName());

    @Async("customTaskExecutor")
    public void doSomethingMethod() {
        log.info("Doing something from an async service, in the thread " + Thread.currentThread().getName());
    }

}
