package com.ftm.vcp.callback;

import org.springframework.context.annotation.Bean;

public class CallbackConfig {

    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public ABean aBean() {
        return new ABean();
    }
}
