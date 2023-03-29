package com.ftm.vcp.beanfactorypostprocessor;

import com.ftm.vcp.beanfactorypostprocessor.config.BeanFactoryPostProcessorConfig;
import com.ftm.vcp.beanfactorypostprocessor.model.Foo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanFactoryPostProcessorDemo {

    public static void main(String[] args) {
        final var applicationContext = new AnnotationConfigApplicationContext(
                BeanFactoryPostProcessorConfig.class);

        final var bean = applicationContext.getBean(Foo.class);
        applicationContext.registerShutdownHook();
    }
}
