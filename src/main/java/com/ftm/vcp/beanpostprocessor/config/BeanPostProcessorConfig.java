package com.ftm.vcp.beanpostprocessor.config;

import com.ftm.vcp.beanpostprocessor.model.Foo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class BeanPostProcessorConfig {

    @Bean // N.B. Declare this `BeanPostProcessor` bean as static to avoid lifetime conflicts
    static Foo foo() {
        return new Foo();
    }
}
