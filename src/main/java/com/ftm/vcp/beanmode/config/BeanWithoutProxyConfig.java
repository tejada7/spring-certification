package com.ftm.vcp.beanmode.config;

import com.ftm.vcp.beanmode.model.Name;
import com.ftm.vcp.beanmode.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @see org.springframework.context.annotation.ConfigurationClassUtils
 */
@Configuration(proxyBeanMethods = false) // This could easily be replaced by @Component
public final class BeanWithoutProxyConfig {

    @Bean("john")
    private Person getPerson(Name name) {
        return new Person(name);
    }

    @Bean
    private Name getName() {
        return new Name("John");
    }
}
