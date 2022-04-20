package com.ftm.vcp.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertiesClass {

    private final Environment environment;
    private final String course;

    public PropertiesClass(final Environment environment, @Value("${course}") final String course) {
        this.environment = environment;
        this.course = course;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public String getCourse() {
        return course;
    }
}
