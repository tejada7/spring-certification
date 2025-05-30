package com.ftm.vcp.properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@SpringJUnitConfig(PropertiesConfig.class)
@DisplayNameGeneration(ReplaceUnderscores.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class FromApplicationFileTest {

    @Autowired
    private PropertiesClass properties;

    @BeforeAll
    static void beforeAll() {
        System.clearProperty("course");
    }

    @Test
    void should_resolve_property_declared_in_application_properties() {
        then(properties.getCourse()).isEqualTo("spring");
    }
}
