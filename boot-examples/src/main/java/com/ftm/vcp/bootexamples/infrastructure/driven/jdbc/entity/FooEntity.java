package com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity;

import com.ftm.vcp.bootexamples.domain.Foo;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("FOO")
public record FooEntity(
    @Id
    String id,
    String name
) {
    public Foo toDomain() {
        return new Foo(id, name);
    }

    public static FooEntity fromDomain(Foo foo) {
        return new FooEntity(foo.id(), foo.name());
    }
}
