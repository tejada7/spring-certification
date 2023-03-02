package com.ftm.vcp.bootexamples.resources;

import com.ftm.vcp.bootexamples.infrastructure.FooRepository;
import com.ftm.vcp.bootexamples.infrastructure.entity.Foo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    private final FooRepository fooRepository;

    public ExampleController(final FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @RequestMapping("{p1}/{p2}")
    public String anEndpoint(@PathVariable final String p1, @PathVariable final String p2) {
        return "ok".transform(p1::concat).transform(p2::concat);
    }

    @GetMapping("/foos")
    public Iterable<Foo> listFoos() {
        return fooRepository.findAll();
    }
}
