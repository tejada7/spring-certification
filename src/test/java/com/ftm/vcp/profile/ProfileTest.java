package com.ftm.vcp.profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@DisplayNameGeneration(ReplaceUnderscores.class)
class ProfileTest {

    private AnnotationConfigApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext();
    }

    @ParameterizedTest(name = "For profile(s) {0}, it should contain {1}")
    @MethodSource("combinations")
    void should_load_bean_according_to_active_profiles(final String[] activeProfiles, final Class<?>... expectedBeans) {
        // Given
        context.register(ProfileBeanConfig.class);
        ofNullable(activeProfiles).ifPresent(
                context.getEnvironment()::setActiveProfiles
        );

        // When
        context.refresh();

        // Then
        stream(expectedBeans)
                .forEach(expectedBean -> assertThat(context.getBean(expectedBean)).isNotNull());
    }

    private static Stream<Arguments> combinations() {
        return Stream.of(
                Arguments.of(null, new Class[]{DefaultBean.class, NoProfileBean.class}),
                Arguments.of(new String[]{"default"}, new Class[]{DefaultBean.class, NoProfileBean.class}),
                Arguments.of(new String[]{"dev"}, new Class[]{DevBean.class, DevOrProdBean.class, NoProfileBean.class}),
                Arguments.of(new String[]{"dev", "prod"},
                             new Class[]{DevBean.class, DevOrProdBean.class, DevAndProdBean.class, NoProfileBean.class}),
                Arguments.of(new String[]{"prod"}, new Class[]{DevOrProdBean.class, NoProfileBean.class})
        );
    }
}
