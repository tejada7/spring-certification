package com.ftm.vcp.beaninitialization;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class ABean implements InitializingBean, DisposableBean {

    @Override
    public void destroy() {
        System.out.println("6. Invoking DisposableBean's destroy method.");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("3. Invoking InitializingBean's afterPropertiesSet method.");
    }

    public ABean() {
        System.out.println("1. Calling aBean's constructor.");
    }

    @PostConstruct // N.B. If the bean implements BeanFactoryPostProcessor, this method is not called
    private void onCreation() {
        System.out.println("2. Invoking @PostContruct method.");
    }

    @PreDestroy // N.B. If the bean implements BeanFactoryPostProcessor, this method is not called
    private void onDestroy() {
        System.out.println("5. Invoking @PreDestroy method.");
    }

    private void customInit() {
        System.out.println("4. Invoking customInit method.");
    }

    private void customDestroy() {
        System.out.println("7. Invoking customDestroy method.");
    }
}
