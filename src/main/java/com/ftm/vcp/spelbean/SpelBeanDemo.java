package com.ftm.vcp.spelbean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpelBeanDemo {

    public static void main(String[] args) {
        final var applicationContext = new AnnotationConfigApplicationContext(SpelBeanConfig.class);
        final var person = applicationContext.getBean(Person.class);
        System.out.println(person);
    }
}
