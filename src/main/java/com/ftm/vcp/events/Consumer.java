package com.ftm.vcp.events;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Consumer {

    private final Logger log = Logger.getLogger(getClass().getName());

    @Async
    @EventListener(condition = "#message.shouldBeConsumed()")
    public void consume(final Message message) {
        log.info("consuming message " + message);
    }
}
