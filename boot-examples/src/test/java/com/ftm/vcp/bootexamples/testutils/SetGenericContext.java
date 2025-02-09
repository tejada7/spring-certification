package com.ftm.vcp.bootexamples.testutils;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(SetContextExtension.class)
public @interface SetGenericContext {
    Class<?> value();

    String errorMessagePattern() default "%s is mandatory";
}
