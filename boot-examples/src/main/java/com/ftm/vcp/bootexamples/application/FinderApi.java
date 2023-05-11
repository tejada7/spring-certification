package com.ftm.vcp.bootexamples.application;

import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;

import java.util.function.Function;

@FunctionalInterface
public interface FinderApi extends Function<String, FooEntity> {

}
