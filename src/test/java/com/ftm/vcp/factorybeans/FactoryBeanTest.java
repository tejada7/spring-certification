package com.ftm.vcp.factorybeans;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@SpringJUnitConfig(FactoryBeanConfig.class)
@DisplayNameGeneration(ReplaceUnderscores.class)
class FactoryBeanTest {

    private final ConfigurationProvider configurationProvider;

    @Autowired
    FactoryBeanTest(final ConfigurationProvider configurationProvider) {
        this.configurationProvider = configurationProvider;
    }

    @Test
    void should_resolve_to_db_based_config() {
        assertThat(configurationProvider).isInstanceOf(DBBasedConfig.class);
    }
}
