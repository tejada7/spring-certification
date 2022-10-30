package com.ftm.vcp.beanmode;

import com.ftm.vcp.beanmode.config.BeanFullModeConfig;
import com.ftm.vcp.beanmode.config.BeanLiteModeConfig;
import com.ftm.vcp.beanmode.model.Name;
import com.ftm.vcp.beanmode.model.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class BeanModeDemo {

    public static void main(String[] args) {
        // Instantiating the object the old-native way
        final var john = new Person(new Name("John"));
        System.out.println("Natively: " + john);

        // Instantiating the object using an IoC container (i.e. Spring's ApplicationContext) lite mode
        final var applicationContext = new AnnotationConfigApplicationContext(BeanLiteModeConfig.class);
        final var john1 = applicationContext.getBean(Person.class);
        System.out.println("Using Spring: " + john1);

        final var name = applicationContext.getBean(Name.class);
        System.out.println(john1.name() == name ? "Same name reference." : "Different name reference.");

        // Instantiating the object using an IoC container (i.e. Spring's ApplicationContext) full mode
        final var applicationContextFullMode = new AnnotationConfigApplicationContext(BeanFullModeConfig.class);
        final var johnFullMode = applicationContextFullMode.getBean(Person.class);
        final var nameFullMode = applicationContextFullMode.getBean(Name.class);

        System.out.println(johnFullMode.name() == nameFullMode ? "Same name reference." : "Different name reference.");
    }
}
