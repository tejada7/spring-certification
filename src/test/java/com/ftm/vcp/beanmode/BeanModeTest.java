package com.ftm.vcp.beanmode;

import com.ftm.vcp.beanmode.config.BeanFullModeConfig;
import com.ftm.vcp.beanmode.config.BeanWithoutProxyConfig;
import com.ftm.vcp.beanmode.config.BeanLiteModeConfig;
import com.ftm.vcp.beanmode.model.Name;
import com.ftm.vcp.beanmode.model.Person;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@DisplayNameGeneration(ReplaceUnderscores.class)
class BeanModeTest {

    @Test
    void should_load_new_instances_when_using_lite_mode() {
        // Given
        final var applicationContext = new AnnotationConfigApplicationContext(BeanLiteModeConfig.class);

        // When
        final var person = applicationContext.getBean(Person.class);
        final var name = applicationContext.getBean(Name.class);

        // Then
        then(person.name()).isNotSameAs(name);
    }

    @Test
    void should_let_spring_create_singleton_beans_when_using_full_mode() {
        // Given
        final var applicationContext = new AnnotationConfigApplicationContext(BeanFullModeConfig.class);

        // When
        final var person = applicationContext.getBean(Person.class);
        final var name = applicationContext.getBean(Name.class);

        // Then
        then(person.name()).isSameAs(name);
    }

    @Test
    void should_still_let_spring_create_singleton_beans_when_using_full_mode_without_cglib_proxy() {
        // Given
        final var applicationContext = new AnnotationConfigApplicationContext(BeanWithoutProxyConfig.class);

        // When
        final var person = applicationContext.getBean(Person.class);
        final var name = applicationContext.getBean(Name.class);

        // Then
        then(person.name()).isSameAs(name);
    }

}
