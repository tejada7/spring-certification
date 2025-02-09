package com.ftm.vcp.bootexamples.application;

import com.ftm.vcp.bootexamples.domain.Foo;

import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface FinderApi extends Function<String, Optional<Foo>> {

}
