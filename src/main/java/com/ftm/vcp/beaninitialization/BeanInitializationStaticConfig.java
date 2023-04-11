package com.ftm.vcp.beaninitialization;

import org.springframework.context.annotation.Bean;

public class BeanInitializationStaticConfig {

    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    static ABeanWithPostProcessors aBeanWithPostProcessors() {
        return new ABeanWithPostProcessors();
    }
}
