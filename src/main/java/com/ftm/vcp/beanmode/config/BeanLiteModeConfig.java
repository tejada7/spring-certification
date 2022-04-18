package com.ftm.vcp.beanmode.config;

import com.ftm.vcp.beanmode.model.Name;
import com.ftm.vcp.beanmode.model.Person;
import org.springframework.context.annotation.Bean;

public class BeanLiteModeConfig {

    @Bean("john")
    public Person getPerson() {
        return new Person(getName());
    }

    @Bean
    public Name getName() {
        return new Name("John");
    }
}
