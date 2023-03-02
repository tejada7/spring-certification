package com.ftm.vcp.bootexamples.infrastructure.entity;

import org.springframework.data.annotation.Id;

public record Foo(
        @Id
        String id,
        String name
) {
}
