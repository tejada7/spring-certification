package com.ftm.vcp.resources;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@SpringJUnitConfig(ResourcesConfig.class)
@DisplayNameGeneration(ReplaceUnderscores.class)
class ResourceTest {

    private final ResourceBean resourceBean;

    @Autowired
    ResourceTest(final ResourceBean resourceBean) {
        this.resourceBean = resourceBean;
    }

    @Test
    void should_retrieve_resource_from_classpath() {
        // Given
        final var expected = "This is a log line.";

        // When
        final var actual = resourceBean.readResourceFromClassPath();

        // Then
        then(actual).isEqualTo(expected);
    }

    @Test
    void should_retrieve_resource_from_file_system() {
        // Given
        final var expected = "This is a log line.";

        // When
        final var actual = resourceBean.readResourceFromFileSystem();

        // Then
        then(actual).isEqualTo(expected);
    }
}
