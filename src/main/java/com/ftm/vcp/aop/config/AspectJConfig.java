package com.ftm.vcp.aop.config;

import com.ftm.vcp.aop.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan(basePackages = "com.ftm.vcp.aop.aspect")
@EnableAspectJAutoProxy
public class AspectJConfig {

    @Bean
    public Person getPerson() {
        return new Person("John Doe");
    }
}
