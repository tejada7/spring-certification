package com.ftm.vcp.factorybeans;

import java.time.Duration;
import java.util.function.Supplier;

import static com.ftm.vcp.factorybeans.ConfigurationProvider.*;

public interface ConfigurationProvider extends Supplier<Config> {

    record Config(String url, Duration timeout) {
    }

}
