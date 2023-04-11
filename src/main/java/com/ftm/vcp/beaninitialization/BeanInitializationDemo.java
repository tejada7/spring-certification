package com.ftm.vcp.beaninitialization;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanInitializationDemo {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BeanInitializationConfig.class);
        context.registerShutdownHook();
    }
}
