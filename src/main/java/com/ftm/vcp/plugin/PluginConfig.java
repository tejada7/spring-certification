package com.ftm.vcp.plugin;

import com.ftm.vcp.plugin.domain.Port;
import com.ftm.vcp.plugin.domain.Repo;
import com.ftm.vcp.plugin.infra.PluginRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.plugin.core.config.EnablePluginRegistries;

@ComponentScan
@Configuration
@EnablePluginRegistries(PluginRepo.class)
public class PluginConfig {

    @Bean
    Port port(final Repo repoStrategy) {
        return new Port(repoStrategy);
    }
}
