package com.ftm.vcp.bootexamples.application;

import com.ftm.vcp.bootexamples.domain.Foo;
import com.ftm.vcp.bootexamples.domain.FooRepository;
import org.springframework.stereotype.Service;

@Service
class Creator implements CreatorApi {

    private final FooRepository fooRepository;

    Creator(final FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @Override
    public Foo apply(String name) {
        return fooRepository.create(new Foo(null, name));
    }
}
