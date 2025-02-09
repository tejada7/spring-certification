package com.ftm.vcp.bootexamples.infrastructure.driven.jdbc;

import com.ftm.vcp.bootexamples.domain.Foo;
import com.ftm.vcp.bootexamples.domain.FooRepository;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Component
class FooJdbcAdapter implements FooRepository {

    private final FooCrudRepository fooCrudRepository;

    FooJdbcAdapter(FooCrudRepository fooCrudRepository) {
        this.fooCrudRepository = fooCrudRepository;
    }

    @Override
    public Optional<Foo> findByIdAndNameLike(String id, String name) {
        return fooCrudRepository.findByIdAndNameLike(id, name)
            .map(FooEntity::toDomain);
    }

    @Override
    public Optional<Foo> findByIdAndName(String id, String name) {
        return fooCrudRepository.findByIdAndName(id, name)
            .map(FooEntity::toDomain);
    }

    @Override
    public Optional<Foo> findByIdAndNameContaining(String id, String name) {
        return fooCrudRepository.findByIdAndNameContaining(id, name)
            .map(FooEntity::toDomain);
    }

    @Override
    public Optional<Foo> findById(String id) {
        return fooCrudRepository.findById(id)
            .map(FooEntity::toDomain);
    }

    @Override
    public List<Foo> findAll() {
        return StreamSupport.stream(fooCrudRepository.findAll().spliterator(), false)
            .map(FooEntity::toDomain)
            .toList();
    }

    @Override
    public Foo create(Foo foo) {
        return fooCrudRepository
            .save(FooEntity.fromDomain(foo))
            .toDomain();
    }
}
