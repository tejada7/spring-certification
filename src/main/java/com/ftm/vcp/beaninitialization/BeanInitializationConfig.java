package com.ftm.vcp.beaninitialization;

import org.springframework.context.annotation.Bean;

public class BeanInitializationConfig {

    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    ABean aBean() {
        return new ABean();
    }
}
