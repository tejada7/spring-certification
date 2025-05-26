package com.ftm.vcp.properties;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@SpringJUnitConfig(PropertiesConfig.class)
@DisplayNameGeneration(ReplaceUnderscores.class)
class FromJvmOptionTest {

    @Autowired
    private PropertiesClass properties;

    private static final String ENV_VARIABLES = "ENV_VARIABLES";
    private static final String JVM_OPTION = "JVM_OPTION";

    @BeforeAll
    static void beforeAll() {
        System.setProperty("course", ENV_VARIABLES);
        System.setProperty("-Dcourse", JVM_OPTION); // This does not set the jvm option!
    }

    @Test
    @Disabled("assuming the jvm parameter was correctly set, this test must pass")
    void when_property_declared_as_jvm_option_should_take_the_highest_precedence() {
        then(properties.getCourse()).isEqualTo(JVM_OPTION);
    }

    @AfterAll
    static void afterAll() {
        System.clearProperty("course");
    }
}
