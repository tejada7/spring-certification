package com.ftm.vcp.bootexamples.infrastructure.driven.aspects;

import com.ftm.vcp.bootexamples.domain.Foo;
import com.ftm.vcp.bootexamples.infrastructure.driven.async.AsynchronousService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
class FooAspects {

    private final Logger log = Logger.getLogger(getClass().getName());

    private final AsynchronousService asynchronousService;

    FooAspects(final AsynchronousService asynchronousService) {
        this.asynchronousService = asynchronousService;
    }

    @Pointcut("execution(* com.ftm.vcp.bootexamples.application.CreatorApi.create(..))")
    private void createMethod() {
        // Do nothing
    }

    @AfterReturning(value = "createMethod()", returning = "foo")
    private void afterCreating(JoinPoint joinPoint, Foo foo) {
        log.info(() -> "After creating a foo " + foo);
        asynchronousService.doSomethingMethod();
    }
}
