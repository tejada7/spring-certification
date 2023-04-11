package com.ftm.vcp.beaninitialization;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class ABeanWithPostProcessors implements InitializingBean, DisposableBean, BeanPostProcessor, BeanFactoryPostProcessor {

    @Override
    public void destroy() {
        System.out.println("7. Invoking DisposableBean's destroy method.");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("2. Invoking DisposableBean's afterPropertiesSet method.");
    }

    public ABeanWithPostProcessors() {
        System.out.println("1. Calling aBean's constructor.");
    }

    private void customInit() {
        System.out.println("3. Invoking customInit method.");
    }

    private void customDestroy() {
        System.out.println("8. Invoking customDestroy method.");
    }

    @Override // N.B. Only called if the bean configuration is static
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("4. Calling post process bean factory...");
    }

    @Override // N.B. Only called if the bean configuration is static
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        System.out.println("5. Calling postProcessBeforeInitialization...");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override // N.B. Only called if the bean configuration is static
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        System.out.println("6. Calling postProcessAfterInitialization...");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
