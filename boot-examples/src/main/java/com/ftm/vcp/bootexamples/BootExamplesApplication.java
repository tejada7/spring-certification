package com.ftm.vcp.bootexamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// --add-opens java.base/java.lang=ALL-UNNAMED --add-modules spring.boot.devtools
@SpringBootApplication
public class BootExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootExamplesApplication.class, args);
    }

}
