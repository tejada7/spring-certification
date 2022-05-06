package com.ftm.vcp.aop;

import com.ftm.vcp.aop.config.AspectJConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AspectDemo {

    public static void main(String[] args) {
        final var applicationContext = new AnnotationConfigApplicationContext(AspectJConfig.class);
        final var person = applicationContext.getBean(Person.class); // AspectJ will create a proxy of person that implements the 'extra stuff;
        person.printFullName();
        System.out.println(person.getClass().getName()); // a cglib Proxy if person does not implement any interface

    }
}
