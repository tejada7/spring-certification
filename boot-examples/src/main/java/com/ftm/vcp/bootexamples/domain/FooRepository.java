package com.ftm.vcp.bootexamples.domain;

import java.util.List;
import java.util.Optional;

public interface FooRepository {
    Optional<Foo> findByIdAndNameLike(String id, String name);

    Optional<Foo> findByIdAndName(String id, String name);

    Optional<Foo> findByIdAndNameContaining(String id, String name);

    Optional<Foo> findById(String id);

    List<Foo> findAll();

    Foo create(Foo foo);
}
