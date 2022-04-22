package com.ftm.vcp.spelbean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {
    private final String fullName;
    private final int age;

    public Person(@Value("${first-name} ${last-name}") final String fullName,
                  @Value("#{T(java.time.Year).now().value - T(Integer).parseInt(${year-of-birth})}") final int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return fullName + ": " + age;
    }
}
