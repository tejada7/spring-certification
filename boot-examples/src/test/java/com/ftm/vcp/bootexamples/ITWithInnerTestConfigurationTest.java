package com.ftm.vcp.bootexamples;

import com.ftm.vcp.bootexamples.application.FinderApi;
import com.ftm.vcp.bootexamples.domain.Foo;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
class ITWithInnerTestConfigurationTest {

    @Test
    void contextLoads(@Autowired final FinderApi finderApi) {
        then(finderApi.apply("123")).isEqualTo(getJohnDoe());
    }

    @TestConfiguration
    static class InnerTestConfig {
        @Bean
        FinderApi finderApi() {
            return id -> getJohnDoe();
        }
    }

    private static Optional<Foo> getJohnDoe() {
        return Optional.of(new Foo("123", "John Doe"));
    }
}
