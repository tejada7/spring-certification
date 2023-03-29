package com.ftm.vcp.beanfactorypostprocessor.config;

import com.ftm.vcp.beanfactorypostprocessor.model.Foo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class BeanFactoryPostProcessorConfig {

    @Bean // N.B. Declare this `BeanFactoryPostProcessor` bean as static to avoid lifetime conflicts
    static Foo foo() {
        return new Foo();
    }
}
