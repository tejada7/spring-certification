package com.ftm.vcp.plugin;

import com.ftm.vcp.plugin.domain.Port;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.plugin.core.PluginRegistry;

public final class PluginDemo {

    public static void main(String[] args) {
        final var applicationContext = new AnnotationConfigApplicationContext(PluginConfig.class);
        final var plugins = applicationContext.getBean(PluginRegistry.class);
        System.out.println(plugins.getPlugins());

        final var port = applicationContext.getBean(Port.class);
        port.save(new Object());
    }
}
