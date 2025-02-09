package com.ftm.vcp.bootexamples.application;

import com.ftm.vcp.bootexamples.domain.Foo;

import java.util.List;
import java.util.function.Supplier;

@FunctionalInterface
public interface MultipleFinderApi extends Supplier<List<Foo>> {

}
