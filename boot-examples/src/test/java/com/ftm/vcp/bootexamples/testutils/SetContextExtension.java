package com.ftm.vcp.bootexamples.testutils;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.platform.commons.support.AnnotationSupport;

/**
 * Extension allowing to transmit a context (e.g. a generic clas type) to a custom {@link ArgumentsProvider}.
 */
public class SetContextExtension implements BeforeAllCallback, Extension {

    private final Class<?> contextType;
    private final String errorMessagePattern;

    /**
     * Used when using
     * <code>
     *
     * @param contextType         the context type to set before running the unit test
     * @param errorMessagePattern the expected error message pattern
     * @RegisterExtension private static final InvalidRecordConstructionExtension customExtension = new InvalidRecordConstructionExtension(Foo.class);
     * </code>
     */
    public SetContextExtension(Class<?> contextType, String errorMessagePattern) {
        this.contextType = contextType;
        this.errorMessagePattern = errorMessagePattern;
    }

    /**
     * Used by the {@link SetGenericContext} annotation
     */
    public SetContextExtension() {
        contextType = null;
        errorMessagePattern = null;
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        final Class<?> recordType = context.getElement()
            .flatMap((annotatedElement) -> AnnotationSupport
                .findAnnotation(annotatedElement, SetGenericContext.class))
            .<Class<?>>map(SetGenericContext::value)
            .orElseGet(() -> contextType);
        context.getStore(ExtensionContext.Namespace.GLOBAL)
            .put("contextType", recordType);

        final String messagePattern = context.getElement()
            .flatMap((annotatedElement) -> AnnotationSupport
                .findAnnotation(annotatedElement, SetGenericContext.class))
            .map(SetGenericContext::errorMessagePattern)
            .orElseGet(() -> errorMessagePattern);
        context.getStore(ExtensionContext.Namespace.GLOBAL)
            .put("errorMessagePattern", messagePattern);
    }
}
