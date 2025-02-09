package com.ftm.vcp.bootexamples.domain;

import java.util.Objects;

public record Foo(
    FooIdentifier id,
    String name
) {
    public Foo {
        Objects.requireNonNull(id, "id cannot be null");
        Objects.requireNonNull(name, "name cannot be null");
    }
}
