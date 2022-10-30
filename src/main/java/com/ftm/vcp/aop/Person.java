package com.ftm.vcp.aop;

/**
 * Mind that the class leveraging aspects cannot be final (i.e. it must instead be extensible).
 */
public final class Person implements Printable {

    private final String fullName;

    public Person(final String fullName) {
        this.fullName = fullName;
    }

    @Override
    public void printFullName() {
        System.out.println(fullName.transform("Full name: "::concat));
    }
}
