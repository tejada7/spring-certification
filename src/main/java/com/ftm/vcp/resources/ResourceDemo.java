package com.ftm.vcp.resources;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class ResourceDemo {

    public static void main(String[] args) throws IOException {
        final var applicationContext = new AnnotationConfigApplicationContext(ResourcesConfig.class);
        final var resourceBean = applicationContext.getBean(ResourceBean.class);
        System.out.println(resourceBean.readResourceFromClassPath());
        System.out.println(resourceBean.readResourceFromFileSystem());
    }
}
