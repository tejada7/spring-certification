package com.ftm.vcp.bootexamples.infrastructure.driven.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean
    TaskExecutor customTaskExecutor() {
        final var executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(15);
        executor.setQueueCapacity(Integer.MAX_VALUE);
        executor.setThreadNamePrefix("customTaskExecutor-");
        executor.initialize();
        return executor;
    }
}
