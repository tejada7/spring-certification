package com.ftm.vcp.bootexamples.infrastructure.driven.jdbc;

import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface EncapsulatedFooRepository {

    Optional<FooEntity> findByIdAndNameLike(String id, String name);

    Optional<FooEntity> findByIdAndName(String id, String name);

    Optional<FooEntity> findByIdAndNameContaining(String id, String name);

    void deleteAll();

    Optional<FooEntity> findById(String id);

    Iterable<FooEntity> findAll();

    FooEntity create(FooEntity entity);
}
