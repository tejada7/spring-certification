package com.ftm.vcp.aop;

/**
 * Mind that the class leveraging aspects cannot be final (i.e. it must instead be extensible).
 */
public final class Person implements Printable, RoleAssignable {

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
}
