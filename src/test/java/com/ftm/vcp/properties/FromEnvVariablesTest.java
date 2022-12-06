package com.ftm.vcp.properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@SpringJUnitConfig(PropertiesConfig.class)
@DisplayNameGeneration(ReplaceUnderscores.class)
class FromEnvVariablesTest {

    private static final String ENV_VARIABLES = "ENV_VARIABLES";

    @Autowired
    private PropertiesClass properties;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("course", ENV_VARIABLES);
    }

    @Test
    void when_property_declared_as_env_option_should_take_higher_precedence_than_application_properties() {
        then(properties.getCourse()).isEqualTo(ENV_VARIABLES);
    }
}
