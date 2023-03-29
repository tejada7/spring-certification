package com.ftm.vcp.callback;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class ABean implements InitializingBean, DisposableBean {

    @Override
    public void destroy() throws Exception {
        System.out.println("Invoking DisposableBean's destroy method.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Invoking DisposableBean's afterPropertiesSet method.");
    }

    public ABean() {
        System.out.println("Calling aBean's constructor.");
    }

    @PostConstruct // N.B, If the bean implements BeanFactoryPostProcessor, this method is not  called
    private void onCreation() {
        System.out.println("Invoking @PostContruct method.");
    }

    @PreDestroy // N.B, If the bean implements BeanFactoryPostProcessor, this method is not  called
    private void onDestroy() {
        System.out.println("Invoking @PreDestroy method.");
    }

    private void customInit() {
        System.out.println("Invoking customInit method.");
    }

    private void customDestroy() {
        System.out.println("Invoking customDestroy method.");
    }
}
