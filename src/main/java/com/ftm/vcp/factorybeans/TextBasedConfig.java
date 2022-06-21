package com.ftm.vcp.factorybeans;

import org.springframework.stereotype.Component;

@Component
public class TextBasedConfig implements ConfigurationProvider {
    @Override
    public Config get() {
        return null;
    }
}
