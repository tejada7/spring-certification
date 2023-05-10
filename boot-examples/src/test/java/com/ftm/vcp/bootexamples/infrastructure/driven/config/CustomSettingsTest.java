package com.ftm.vcp.bootexamples.infrastructure.driven.config;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SpringBootTest
class CustomSettingsTest {

    private final CustomSettings customSettings;
    private final String expected;

    @Autowired
    CustomSettingsTest(final CustomSettings customSettings,
                       @Value("${custom-configuration.foo}") final String expected) {
        this.customSettings = customSettings;
        this.expected = expected;
    }

    @Test
    void should_load_custom_settings_from_application_properties_file() {
        thenSoftly(softly -> {
            softly.then(customSettings).isNotNull();
            softly.then(customSettings.foo()).isEqualTo(expected);
        });
    }
}
