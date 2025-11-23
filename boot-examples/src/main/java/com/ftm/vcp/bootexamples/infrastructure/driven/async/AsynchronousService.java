package com.ftm.vcp.bootexamples.infrastructure.driven.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class AsynchronousService {

    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    @Async("customTaskExecutor")
    public void doSomethingMethod() {
        log.info("Doing something from an async service, in the thread " + Thread.currentThread().getName());
    }

}
