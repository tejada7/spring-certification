package com.ftm.vcp.bootexamples.infrastructure.driven.jdbc;

import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface FooRepository extends CrudRepository<FooEntity, String>, EncapsulatedFooRepository {

    Optional<FooEntity> findByIdAndNameLike(String id, String name);

    Optional<FooEntity> findByIdAndName(String id, String name);

    Optional<FooEntity> findByIdAndNameContaining(String id, String name);

    @Override
    default FooEntity create(FooEntity entity) {
        return save(entity);
    }
}
