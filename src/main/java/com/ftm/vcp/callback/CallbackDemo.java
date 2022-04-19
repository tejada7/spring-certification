package com.ftm.vcp.callback;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CallbackDemo {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(CallbackConfig.class);
        context.registerShutdownHook();
    }
}
