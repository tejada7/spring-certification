package com.ftm.vcp.spelbean;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;
import static org.junit.jupiter.api.DisplayNameGenerator.IndicativeSentences;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@DisplayName("A bean with spel")
@DisplayNameGeneration(IndicativeSentences.class)
@IndicativeSentencesGeneration(separator = " ", generator = ReplaceUnderscores.class)
@SpringJUnitConfig(SpelBeanConfig.class)
class SpelBeanTest {

    private final Person person;

    @Autowired
    SpelBeanTest(final Person person) {
        this.person = person;
    }

    @Test
    void should_resolve_values_from_property_file() {
        thenSoftly(softly -> {
            softly.then(person.getAge()).isGreaterThanOrEqualTo(30);
            softly.then(person.getFullName()).isEqualTo("John Doe");
        });
    }
}
