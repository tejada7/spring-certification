package com.ftm.vcp.bootexamples.infrastructure.driven.config;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SpringJUnitConfig(ConditionalBean.class)
@TestPropertySource("classpath:/application-with-feature-flag-enabled.yaml")
class ConditionalExistingBeanTest {

    @Test
    void should_initialize_conditional_bean_when_condition_met(@Autowired ApplicationContext applicationContext) {
        assertThat(applicationContext.getBean(ConditionalBean.class)).isNotNull();
    }
}
