package com.ftm.vcp.factorybeans;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
// ℹ️ Unless FactoryBean, AbstractFactoryBean lies allows to create singleton/prototype beans.
public final class ConfigFactoryBean implements FactoryBean<ConfigurationProvider> {

    private static final boolean FLAG = true;

    @Override
    public ConfigurationProvider getObject() {
        return FLAG
                ? new DBBasedConfig()
                : new TextBasedConfig();
    }

    @Override
    public Class<?> getObjectType() {
        return ConfigurationProvider.class;
    }
}
