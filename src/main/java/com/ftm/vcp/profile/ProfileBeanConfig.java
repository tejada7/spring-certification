package com.ftm.vcp.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan
public class ProfileBeanConfig {

    @Bean
    @Profile("default")
    public DefaultBean defaultBean() {
        return new DefaultBean();
    }

    @Bean
    public NoProfileBean noProfileBean() {
        return new NoProfileBean();
    }
}
