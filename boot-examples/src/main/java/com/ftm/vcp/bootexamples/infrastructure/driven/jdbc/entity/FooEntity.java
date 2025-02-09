package com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity;

import com.ftm.vcp.bootexamples.domain.Foo;
import com.ftm.vcp.bootexamples.domain.FooIdentifier;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("FOO")
public record FooEntity(
    @Id
    String id,
    String name
) {
    public Foo toDomain() {
        return new Foo(FooIdentifier.of(id), name);
    }

    public static FooEntity fromDomain(Foo foo) {
        return new FooEntity(switch (foo.id()) {
            case FooIdentifier.NoIdentifier() -> null;
            case FooIdentifier.ExistingIdentifier(var value) -> value;
        }, foo.name());
    }
}
