package com.ftm.vcp.bootexamples;

import com.ftm.vcp.bootexamples.application.FinderApi;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
class ExternalTestConfiguration {

    @Bean
    FinderApi finderApi() {
        return id -> getJohnDoe();
    }

    static FooEntity getJohnDoe() {
        return new FooEntity("123", "John Doe");
    }
}
