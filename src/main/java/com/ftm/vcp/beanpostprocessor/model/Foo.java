package com.ftm.vcp.beanpostprocessor.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class Foo implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        System.out.println("Calling postProcessBeforeInitialization...");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        System.out.println("Calling postProcessAfterInitialization...");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    public Foo() {
        System.out.println("Initializing foo...");
    }
}
