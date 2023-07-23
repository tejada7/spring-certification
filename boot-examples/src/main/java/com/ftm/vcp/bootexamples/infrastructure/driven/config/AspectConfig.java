package com.ftm.vcp.bootexamples.infrastructure.driven.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy// (proxyTargetClass = true) // → to force CGlib even when dealing with interfaces
class AspectConfig {
}
