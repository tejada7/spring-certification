package com.ftm.vcp.bootexamples.application;

import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.EncapsulatedFooRepository;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.springframework.stereotype.Service;

@Service
public class Finder implements FinderApi {

    private final EncapsulatedFooRepository fooRepository;

    public Finder(final EncapsulatedFooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @Override
    public FooEntity apply(final String id) {
        fooRepository.deleteAll();
        return fooRepository.findById(id).orElse(null);
    }
}
