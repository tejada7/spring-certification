package com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("FOO")
public record FooEntity(
        @Id
        String id,
        String name
) {
}
