package com.ftm.vcp.bootexamples.domain;

import java.util.Optional;

public sealed interface FooIdentifier {

    record NoIdentifier() implements FooIdentifier {

        @Override
        public String toString() {
            return "";
        }
    }

    record ExistingIdentifier(String value) implements FooIdentifier {

        @Override
        public String toString() {
            return value;
        }
    }

    public static FooIdentifier of(String value) {
        return Optional.ofNullable(value)
            .<FooIdentifier>map(ExistingIdentifier::new)
            .orElseGet(NoIdentifier::new);
    }

    public static FooIdentifier noIdentifier() {
        return of(null);
    }
}
