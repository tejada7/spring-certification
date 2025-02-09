package com.ftm.vcp.bootexamples.application;

import com.ftm.vcp.bootexamples.domain.Foo;
import com.ftm.vcp.bootexamples.domain.FooRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class MultipleFinder implements MultipleFinderApi {

    private final FooRepository fooRepository;

    MultipleFinder(final FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @Override
    public List<Foo> get() {
        return fooRepository.findAll();
    }
}
