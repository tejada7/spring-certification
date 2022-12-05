package com.ftm.vcp.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

@Component
public class Publisher {

    private final ApplicationEventPublisher eventPublisher;

    public Publisher(final ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publish() throws InterruptedException {
        final var scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        try {
            LongStream.range(0, 5)
                    .forEach(i -> {
                        final var message = new Message("Message " + i);
                        scheduledExecutorService.schedule(() -> eventPublisher.publishEvent(message),
                                                          5 - i,
                                                          TimeUnit.SECONDS);
                    });
        } finally {
            scheduledExecutorService.awaitTermination(7, TimeUnit.SECONDS);
            scheduledExecutorService.shutdown();
        }
    }
}
