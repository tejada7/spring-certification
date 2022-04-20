package com.ftm.vcp.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static java.lang.System.out;

public class ProfileDemo {

    public static void main(String[] args) {
        final var applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanConfig.class);
        applicationContext.getEnvironment().setActiveProfiles("dev", "prod");
        applicationContext.refresh();
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .filter(bean -> !bean.startsWith("org.springframework"))
                .filter(bean -> !bean.startsWith("beanConfig"))
                .forEach(out::println);
    }
}
