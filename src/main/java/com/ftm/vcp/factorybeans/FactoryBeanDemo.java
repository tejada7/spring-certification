package com.ftm.vcp.factorybeans;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FactoryBeanDemo {

    public static void main(String[] args) {
        final var context = new AnnotationConfigApplicationContext(ConfigFactoryBean.class);
        final var bean = context.getBean(ConfigurationProvider.class);
        System.out.println(bean.getClass()); // Should be an instance of com.ftm.vcp.factorybeans.DBBasedConfig
    }
}
