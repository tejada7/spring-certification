package com.ftm.vcp.aop.aspect;

import com.ftm.vcp.aop.Person;
import com.ftm.vcp.aop.Role;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;

@Aspect
@Component
class AspectJAspect {

    @Before("execution(* printFullName())")
    private void log(JoinPoint joinPoint) { // ℹ️ If used, JointPoint must be the first argument of the declared method.
        System.out.println("Printed by an advice, within the method %s."
                .formatted(joinPoint.getSignature().getName()));
    }

    @Around(value = "execution(* assign(*)) && args(role) && target(person)", argNames = "proceedingJoinPoint,role,person")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, // ℹ️ If used, JoinPoint must be the first argument of the declared method.
            Role role, Person person) throws Throwable {
        System.out.printf("Assigning %s to %s%n", role, person.getFullName());
        final var start = Instant.now();
        final var result = proceedingJoinPoint.proceed();
        System.out.printf("Elapsed time: %s nano-seconds%n", Duration.between(start, Instant.now()).toNanos());
        return result;
    }

    @Pointcut("within(com.ftm.vcp.aop.Person)")
    private void withinPointCut() {
    }

    @Before(value = "withinPointCut() && this(it)", argNames = "joinPoint, it")
    private void withinAdvice(JoinPoint joinPoint, Object it) {
        System.out.println("Printing from within advice.");
    }

    @Pointcut("target(com.ftm.vcp.aop.Printable))")
    private void targetPointCut() {
    }

    @Before("targetPointCut() && execution(void print*(..))")
    private void targetAdvice() {
        System.out.println("Printed by targetAdvice.");
    }

    @Pointcut("execution(* getName())")
    private void getNamePointCut() {
    }

    @AfterReturning(pointcut = "getNamePointCut()", returning = "name")
    private void printMethodResult(JoinPoint joinPoint, String name) {
        System.out.printf("The result of getName() method is '%s'", name);
    }

    @Pointcut("execution(* methodThrowingRuntimeException(..))")
    private void methodThrowingRuntimeExceptionPointCut() {
    }

    @AfterThrowing(pointcut = "methodThrowingRuntimeExceptionPointCut()", throwing = "exception")
    private void handleExceptionThrown(JoinPoint joinPoint, Exception exception) {
        System.out.printf("The exception thrown was '%s'", exception);
    }
}
