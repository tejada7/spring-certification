package com.ftm.vcp.bootexamples.infrastructure.driving.rest;

import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.FooRepository;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    public static final String PROTECTED_DEFAULT_FOOS_URL = "/protected/default/foos";
    public static final String PROTECTED_HTTP_BASIC_FOOS_URL = "/protected/http-basic/foos";
    private final FooRepository fooRepository;

    public ExampleController(final FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @RequestMapping("{p1}/{p2}")
    public String anEndpoint(@PathVariable final String p1, @PathVariable final String p2) {
        return "ok".transform(p1::concat).transform(p2::concat);
    }

    @GetMapping(PROTECTED_DEFAULT_FOOS_URL)
    public Iterable<FooEntity> listFoos() {
        return fooRepository.findAll();
    }

    @PreAuthorize("hasRole('HTTP_BASIC_USER')")
    @GetMapping(PROTECTED_HTTP_BASIC_FOOS_URL)
    public Iterable<FooEntity> listFoosProtectedWithHttpBasic() {
        return fooRepository.findAll();
    }
}
