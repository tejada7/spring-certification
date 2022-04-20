package com.ftm.vcp.profile;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Profile("dev & prod")
@Retention(RetentionPolicy.RUNTIME)
public @interface DevAndProd {
}
