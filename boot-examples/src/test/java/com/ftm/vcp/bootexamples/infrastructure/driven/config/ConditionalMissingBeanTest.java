package com.ftm.vcp.bootexamples.infrastructure.driven.config;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SpringJUnitConfig(ConditionalBean.class)
@TestPropertySource(value = "classpath:/application-with-feature-flag-enabled.yaml", properties = "feature-flag=false")
class ConditionalMissingBeanTest {

    @Test
    void should_not_initialize_conditional_bean_when_condition_absent(@Autowired ApplicationContext applicationContext) {
        assertThatThrownBy(() -> applicationContext.getBean(ConditionalBean.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }
}
