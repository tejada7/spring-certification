package com.ftm.vcp.properties;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("classpath:app.properties")
public class PropertiesConfig {
}
