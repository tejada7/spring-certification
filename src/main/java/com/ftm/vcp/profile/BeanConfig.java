package com.ftm.vcp.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan
public class BeanConfig {

    @Bean
    @Profile("default")
    public DefaultBean defaultBean() {
        return new DefaultBean();
    }
}
