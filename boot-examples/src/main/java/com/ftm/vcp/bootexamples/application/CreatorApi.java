package com.ftm.vcp.bootexamples.application;

import com.ftm.vcp.bootexamples.domain.Foo;

import java.util.function.Function;
import java.util.function.UnaryOperator;

@FunctionalInterface
public interface CreatorApi extends Function<String, Foo> {

    default Foo create(String id) {
        return apply(id);
    }
}
