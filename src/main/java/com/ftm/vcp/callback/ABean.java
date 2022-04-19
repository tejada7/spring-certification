package com.ftm.vcp.callback;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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

    @PostConstruct
    private void onCreation() {
        System.out.println("Invoking @PostContruct method.");
    }

    @PreDestroy
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
