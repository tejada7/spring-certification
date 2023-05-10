package com.ftm.vcp.bootexamples.infrastructure.driven.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties} leverages <b>relaxed binding</b>, meaning that the below examples are equivalent:
 * <ul>
 * <li>custom-configuration.foo</li>
 * <li>customConfiguration.foo</li>
 * <li>custom_configuration.foo</li>
 * <li>CUSTOM_CONFIGURATION_FOO</li>
 * </ul>
 */
@ConfigurationProperties("custom-configuration")
public record CustomSettings(String foo) {
}
