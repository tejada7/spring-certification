package com.ftm.vcp.bootexamples.infrastructure.driven.config;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SpringJUnitConfig(ConditionalBean.class)
class ConditionalBeanTest {

    @Nested
    @TestPropertySource(properties = "feature-flag=true")
    class ExistenceCase {
        @Test
        void should_initialize_conditional_bean_when_condition_met(ApplicationContext applicationContext) {
            assertThat(applicationContext.getBean(ConditionalBean.class)).isNotNull();
        }
    }

    @Nested
    @TestPropertySource(properties = "feature-flag=false")
    class MissingCase {
        @Test
        void should_not_initialize_conditional_bean_when_condition_absent(ApplicationContext applicationContext) {
            assertThatThrownBy(() -> applicationContext.getBean(ConditionalBean.class))
                    .isInstanceOf(NoSuchBeanDefinitionException.class);
        }
    }
}
