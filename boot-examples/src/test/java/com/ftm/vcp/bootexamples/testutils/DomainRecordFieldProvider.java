package com.ftm.vcp.bootexamples.testutils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Generates arguments as number of fields for a given record, along with an expected error message
 */
public class DomainRecordFieldProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        final Class<?> contextType = (Class<?>) context.getStore(ExtensionContext.Namespace.GLOBAL).get("contextType");
        final String errorMessagePattern = (String) context.getStore(ExtensionContext.Namespace.GLOBAL).get("errorMessagePattern");
        return Arrays.stream(contextType.getRecordComponents())
            .map(RecordComponent::getName)
            .map(fieldName -> new Object() {
                final String field = fieldName;
                final String expectedMessage = Optional.ofNullable(errorMessagePattern)
                    .map(string -> string.formatted(field))
                    .orElseGet(() -> fieldName + " cannot be null");
            })
            .map(result -> Arguments.arguments(result.field, result.expectedMessage));
    }
}
