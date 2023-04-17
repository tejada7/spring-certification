package com.ftm.vcp.aop.aspect;

import com.ftm.vcp.aop.Person;
import com.ftm.vcp.aop.Role;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
class AspectJAspect {

    @Before("execution(* printFullName())")
    private void log(JoinPoint joinPoint) {
        System.out.println("Printed by an advice, within the method %s."
                                   .formatted(joinPoint.getSignature().getName()));
    }

    @Around(value = "execution(* assign(*)) && args(role) && target(person)", argNames = "proceedingJoinPoint,role,person")
    public Object around(ProceedingJoinPoint proceedingJoinPoint,
                         Role role, Person person) throws Throwable {
        System.out.printf("Assigning %s to %s%n", role, person.getFullName());
        return proceedingJoinPoint.proceed();
    }
}
