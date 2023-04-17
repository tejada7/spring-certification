package com.ftm.vcp.aop;

import com.ftm.vcp.aop.config.AspectJConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class AspectDemo {

    public static void main(String[] args) {
        final var applicationContext = new AnnotationConfigApplicationContext(AspectJConfig.class);
        final var printable = applicationContext.getBean(Printable.class); // AspectJ will create a proxy of printable that implements the 'extra stuff;
        printable.printFullName();
        final var roleAssignable = applicationContext.getBean(RoleAssignable.class);
        roleAssignable.assign(Role.ADMIN);
        System.out.println(printable.getClass().getName()); // a jdk dynamic Proxy if person does implement any interface, in this case printable
    }
}
