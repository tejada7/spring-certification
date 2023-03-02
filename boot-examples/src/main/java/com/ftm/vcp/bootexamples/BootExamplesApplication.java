package com.ftm.vcp.bootexamples;

import com.ftm.vcp.bootexamples.infrastructure.FooRepository;
import com.ftm.vcp.bootexamples.infrastructure.entity.Foo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// --add-opens java.base/java.lang=ALL-UNNAMED --add-modules spring.boot.devtools
@SpringBootApplication
public class BootExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootExamplesApplication.class, args);
    }

    @Bean
    CommandLineRunner start(FooRepository fooRepository) {
        return args -> fooRepository.save(new Foo(null, "foo4"));
    }

}