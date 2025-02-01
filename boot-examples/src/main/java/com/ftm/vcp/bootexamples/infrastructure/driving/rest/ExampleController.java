package com.ftm.vcp.bootexamples.infrastructure.driving.rest;

import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.EncapsulatedFooRepository;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ExampleController {

    public static final String PROTECTED_CSRF_FOOS_URL = "/protected/csrf/foos";
    public static final String PROTECTED_HTTP_BASIC_FOOS_URL = "/protected/http-basic/foos";
    public static final String PROTECTED_LOGIN_URL = "/protected/resource";

    private final EncapsulatedFooRepository fooRepository;

    public ExampleController(final EncapsulatedFooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @RequestMapping("{p1}/{p2}")
    public String anEndpoint(@PathVariable final String p1, @PathVariable final String p2) {
        return "ok".transform(p1::concat).transform(p2::concat);
    }

    @GetMapping(PROTECTED_CSRF_FOOS_URL)
    public Iterable<FooEntity> listFoos() {
        return fooRepository.findAll();
    }

    @PreAuthorize("hasRole('HTTP_BASIC_USER')")
    @GetMapping(PROTECTED_HTTP_BASIC_FOOS_URL)
    public Iterable<FooEntity> listFoosProtectedWithHttpBasic() {
        return fooRepository.findAll();
    }

    @PostMapping(PROTECTED_CSRF_FOOS_URL)
    public ResponseEntity<Void> create() {
        final var savedFoo = fooRepository.create(new FooEntity(null, "a new foo"));
        return ResponseEntity.created(ServletUriComponentsBuilder
                                     .fromCurrentRequestUri()
                                     .path("/{fooId}")
                                     .buildAndExpand(savedFoo.id()).toUri())
                             .build();
    }

    @GetMapping(PROTECTED_LOGIN_URL)
     String protectedResource() {
        return "✅ successfully authenticated!";
    }

    @GetMapping("/ott/sent")
    String ottSent() {
        return "Magic link valid for 5 minutes, please check the logs.";
    }
}
