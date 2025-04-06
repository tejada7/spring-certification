package com.ftm.vcp.bootexamples.testutils;

import com.ftm.vcp.bootexamples.testutils.JsonAssert.JsonAssertImpl;
import org.junit.jupiter.api.extension.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class JsonAssertExtension implements BeforeEachCallback, ParameterResolver {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        getStore(context).getOrComputeIfAbsent(JsonAssert.class,
            key -> new JsonAssertImpl(initLogConsole()));
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return JsonAssert.class.equals(parameterContext.getParameter().getType());
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return getJsonAssert(extensionContext);
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(getClass()));
    }

    private ByteArrayOutputStream initLogConsole() {
        final ByteArrayOutputStream logConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(logConsole));
        return logConsole;
    }

    private JsonAssert getJsonAssert(ExtensionContext context) {
        return getStore(context).get(JsonAssert.class, JsonAssertImpl.class);
    }
}
