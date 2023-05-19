package com.ftm.vcp.bootexamples.application;

import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.FooRepository;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FinderTest {

    @Test
    void should_find_entity(@Mock FooRepository fooMockedRepository) {
        // Arrange
        final var finder = new Finder(fooMockedRepository);
        final String anId = "123";
        final var expected = new FooEntity("123", "existingFoo");
        given(fooMockedRepository.findById(anId)).willReturn(Optional.of(expected));

        // Act
        final var actual = finder.apply(anId);

        // Assert
        then(actual).isEqualTo(expected);
    }
}
