package com.ftm.vcp.bootexamples.application;

import com.ftm.vcp.bootexamples.domain.Foo;
import com.ftm.vcp.bootexamples.domain.FooIdentifier;
import com.ftm.vcp.bootexamples.domain.FooRepository;
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
        final var expected = new Foo(FooIdentifier.of("123"), "existingFoo");
        given(fooMockedRepository.findById(anId)).willReturn(Optional.of(expected));

        // Act
        final var actual = finder.apply(anId);

        // Assert
        then(actual).contains(expected);
    }
}
