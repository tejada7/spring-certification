package com.ftm.vcp.bootexamples;

import com.ftm.vcp.bootexamples.infrastructure.driven.config.CustomSettings;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.FooRepository;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

// --add-opens java.base/java.lang=ALL-UNNAMED --add-modules spring.boot.devtools
@SpringBootApplication
@EnableConfigurationProperties(CustomSettings.class)
public class BootExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootExamplesApplication.class, args);
    }

    @Bean
    CommandLineRunner start(FooRepository fooRepository) {
        return args -> fooRepository.save(new FooEntity(null, "foo4"));
    }

}
