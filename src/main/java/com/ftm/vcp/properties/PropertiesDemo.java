package com.ftm.vcp.properties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Mind that configuration in Spring takes the following precedence:
 * first level the jvm options, e.g. -Denvironment=value
 * second level the OS environment variable, e.g. ENVIRONMENT=value
 * third level, a properties file, e.g. app.properties
 */
public class PropertiesDemo {
    public static void main(String[] args) {
        final var applicationContext = new AnnotationConfigApplicationContext(PropertiesConfig.class);
        final var environment = applicationContext.getBean(PropertiesClass.class).getEnvironment().getProperty(
                "environment");
        final var course = applicationContext.getBean(PropertiesClass.class).getCourse();
        System.out.println(environment + " " + course);
    }
}
