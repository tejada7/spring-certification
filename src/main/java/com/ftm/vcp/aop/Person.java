package com.ftm.vcp.aop;

/**
 * Mind that the class leveraging aspects and being adviced by a jdk proxy-based aspect cannot be final (i.e. it must instead implement a bean).
 */
public final class Person implements Printable, RoleAssignable, Nameable, ExceptionThrower { // Beware that makin

    private final String fullName;
    private Role role;

    public Person(final String fullName) {
        this.fullName = fullName;
    }

    @Override
    public void printFullName() {
        System.out.println(fullName.transform("Full name: "::concat));
    }

    @Override
    public void assign(final Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String getName() {
        return fullName;
    }

    @Override
    public void methodThrowingRuntimeException() {
        throw new RuntimeException();
    }
}
