package com.ftm.vcp.aop;

/**
 * Mind that the class leveraging aspects cannot be final (i.e. it must instead be extensible).
 */
public class Person implements Printable {

    private final String fullName;

    public Person(final String fullName) {
        this.fullName = fullName;
    }

    @Override
    public void printFullName() {
        System.out.println("Full name: " + fullName);
    }
}
