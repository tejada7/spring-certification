package com.ftm.vcp.events;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EventDemo {

    public static void main(String[] args) throws InterruptedException {
        final var context = new AnnotationConfigApplicationContext(EventConfig.class);
        final var publisher = context.getBean(Publisher.class);
        publisher.publish();
        context.registerShutdownHook();
    }
}
