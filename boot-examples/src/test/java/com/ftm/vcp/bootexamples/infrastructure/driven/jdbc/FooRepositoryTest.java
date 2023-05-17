package com.ftm.vcp.bootexamples.infrastructure.driven.jdbc;

import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;

@DataJdbcTest
@DisplayNameGeneration(ReplaceUnderscores.class)
class FooRepositoryTest {

    @Autowired
    private FooRepository fooRepository;

    @Test
    void should_find_by_id_and_name_like() {
        final var savedEntity = fooRepository.save(new FooEntity(null, "toto"));

        thenSoftly(softly -> {
            softly.then(fooRepository.findByIdAndNameLike(savedEntity.id(), "%ot%")).isPresent();
            softly.then(fooRepository.findByIdAndName(savedEntity.id(), "toto")).isPresent();
            softly.then(fooRepository.findByIdAndNameContaining(savedEntity.id(), "tot")).isPresent();
        });
    }
}
