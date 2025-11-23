package com.ftm.vcp.bootexamples.infrastructure.driven.jdbc;

import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jdbc.test.autoconfigure.DataJdbcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;

@DataJdbcTest
@Import(FooJdbcAdapter.class)
@DisplayNameGeneration(ReplaceUnderscores.class)
class FooCrudRepositoryTest {

    @Autowired
    private FooCrudRepository fooCrudRepository;

    @Test
    void should_find_by_id_and_name_like() {
        final var savedEntity = fooCrudRepository.save(new FooEntity(null, "toto"));

        thenSoftly(softly -> {
            softly.then(fooCrudRepository.findByIdAndNameLike(savedEntity.id(), "%ot%")).isPresent();
            softly.then(fooCrudRepository.findByIdAndName(savedEntity.id(), "toto")).isPresent();
            softly.then(fooCrudRepository.findByIdAndNameContaining(savedEntity.id(), "tot")).isPresent();
        });
    }
}
