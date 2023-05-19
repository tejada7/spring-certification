package com.ftm.vcp.bootexamples.application;

import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.FooRepository;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.springframework.stereotype.Service;

@Service
public class Finder implements FinderApi {

    private final FooRepository fooRepository;

    public Finder(final FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @Override
    public FooEntity apply(final String id) {
        return fooRepository.findById(id).orElse(null);
    }
}
