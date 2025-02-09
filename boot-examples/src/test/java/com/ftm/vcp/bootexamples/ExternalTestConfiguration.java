package com.ftm.vcp.bootexamples;

import com.ftm.vcp.bootexamples.application.FinderApi;
import com.ftm.vcp.bootexamples.domain.Foo;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@TestConfiguration
class ExternalTestConfiguration {

    @Bean
    FinderApi finderApi() {
        return id -> getJohnDoe();
    }

    static Optional<Foo> getJohnDoe() {
        return Optional.of(new Foo("123", "John Doe"));
    }
}
