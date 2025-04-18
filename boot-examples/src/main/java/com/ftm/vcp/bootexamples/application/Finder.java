package com.ftm.vcp.bootexamples.application;

import com.ftm.vcp.bootexamples.domain.Foo;
import com.ftm.vcp.bootexamples.domain.FooRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class Finder implements FinderApi {

    private final FooRepository fooRepository;

    Finder(final FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @Override
    public Optional<Foo> apply(final String id) {
        return fooRepository.findById(id);
    }
}
