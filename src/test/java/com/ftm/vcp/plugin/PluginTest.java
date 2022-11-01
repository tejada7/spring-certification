package com.ftm.vcp.plugin;

import com.ftm.vcp.plugin.domain.Port;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@SpringJUnitConfig(PluginConfig.class)
@DisplayNameGeneration(ReplaceUnderscores.class)
@ExtendWith(OutputCaptureExtension.class)
class PluginTest {

    private final Port port;

    @Autowired
    PluginTest(final Port port) {
        this.port = port;
    }

    @Test
    void should_run_save_for_db_and_http_but_return_result_of_db(final CapturedOutput output) {
        // Given
        final var entityToSave = new Object();

        // When
        final var actual = port.save(entityToSave);

        // Then
        thenSoftly(softly -> {
            softly.then(actual).isEqualTo("saved from db repo");
            softly.then(output.toString()).contains(
                    "save from DbRepo",
                    "save from HttpRepo"
            );
        });
    }

    @Test
    void should_delete_element_by_only_calling_db_plugin(final CapturedOutput output) {
        // Given
        final var anId = "anId";

        // When
        final var actual = port.delete(anId);

        // Then
        thenSoftly(softly -> {
            softly.then(actual).isEqualTo(2);
            softly.then(output.toString())
                    .contains("delete from DbRepo")
                    .doesNotContain("delete from HttpRepo");
        });
    }
}
