package com.ftm.vcp.bootexamples.infrastructure.driven.config;

import org.springframework.security.authentication.ott.OneTimeToken;

interface OneTimeTokenContextProvider {

    OneTimeTokenContext get();

    void set(OneTimeTokenContext oneTimeToken);

    void clear();

    sealed interface OneTimeTokenContext {
    }

    record MissingOneTimeTokenContext() implements OneTimeTokenContext {
    }

    record ExistingOneTimeTokenContext(OneTimeToken oneTimeToken) implements OneTimeTokenContext {
    }

}
