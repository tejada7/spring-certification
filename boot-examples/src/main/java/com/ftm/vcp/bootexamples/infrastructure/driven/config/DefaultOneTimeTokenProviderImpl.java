package com.ftm.vcp.bootexamples.infrastructure.driven.config;

import org.springframework.stereotype.Component;

@Component
public class DefaultOneTimeTokenProviderImpl implements OneTimeTokenContextProvider {

    private final ThreadLocal<OneTimeTokenContext> threadLocal = new ThreadLocal<>();

    @Override
    public OneTimeTokenContext get() {
        return threadLocal.get();
    }

    @Override
    public void set(OneTimeTokenContext oneTimeToken) {
        threadLocal.set(oneTimeToken);
    }

    @Override
    public void clear() {
        threadLocal.remove();
    }
}
