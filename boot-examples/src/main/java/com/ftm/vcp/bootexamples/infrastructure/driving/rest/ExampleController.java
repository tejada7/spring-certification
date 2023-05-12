package com.ftm.vcp.bootexamples.infrastructure.driving.rest;

import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.FooRepository;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @PostMapping(PROTECTED_DEFAULT_FOOS_URL)
    public ResponseEntity<Void> create() {
        final var savedFoo = fooRepository.save(new FooEntity(null, "a new foo"));
        return ResponseEntity.created(ServletUriComponentsBuilder
                                              .fromCurrentRequestUri()
                                              .path("/{fooId}")
                                              .buildAndExpand(savedFoo.id()).toUri())
                .build();
    }
}
