package com.ftm.vcp.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
class AspectJAspect {

    @Before("execution(* printFullName())")
    private void log() {
        System.out.println("Printed by an advice.");
    }
}
