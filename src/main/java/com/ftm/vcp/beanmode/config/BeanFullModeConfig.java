package com.ftm.vcp.beanmode.config;

import com.ftm.vcp.beanmode.model.Name;
import com.ftm.vcp.beanmode.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFullModeConfig {

    @Bean("john")
    Person getPerson() {
        return new Person(getName());
    }

    @Bean
    Name getName() {
        return new Name("John");
    }
}
