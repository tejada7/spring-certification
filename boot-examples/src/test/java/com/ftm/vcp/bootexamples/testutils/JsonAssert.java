package com.ftm.vcp.bootexamples.testutils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.assertj.core.api.BDDAssertions.then;

public interface JsonAssert {

    JsonAssert hasAnyInfoLog();

    void verify();

    public record JsonAssertImpl(
        ByteArrayOutputStream outputStream,
        AtomicBoolean anyInfoLog
    ) implements JsonAssert {

        public JsonAssertImpl(ByteArrayOutputStream outputStream) {
            this(outputStream, new AtomicBoolean(false));
        }

        @Override
        public JsonAssert hasAnyInfoLog() {
            anyInfoLog.set(true);
            return this;
        }

        @Override
        public void verify() {
            final var logAsPlainText = outputStream.toString();
            final var json = logAsPlainText.lines()
                .filter(isJson())
                .map(toJson())
                .toList();
            if (anyInfoLog.get()) {
                then(json.stream().anyMatch(it -> {
                    try {
                        return it.getString("level").equalsIgnoreCase("info");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                })).isTrue();
            }
        }

        private static @NotNull Function<String, JSONObject> toJson() {
            return line -> {
                try {
                    return new JSONObject(line);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            };
        }

        private static @NotNull Predicate<String> isJson() {
            return it -> it.startsWith("{\"");
        }
    }
}
