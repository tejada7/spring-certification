package com.ftm.vcp.beanpostprocessor;

import com.ftm.vcp.beanpostprocessor.config.BeanPostProcessorConfig;
import com.ftm.vcp.beanpostprocessor.model.Foo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanPostProcessorDemo {

    public static void main(String[] args) {
        final var applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);
        final var foo = applicationContext.getBean(Foo.class);
        applicationContext.registerShutdownHook();
    }
}
