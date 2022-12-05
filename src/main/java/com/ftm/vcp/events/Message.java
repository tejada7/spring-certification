package com.ftm.vcp.events;

import java.util.random.RandomGeneratorFactory;

public record Message(String value) {

    @Override
    public String toString() {
        return value;
    }

    public boolean shouldBeConsumed() {
        return RandomGeneratorFactory.of("Random").create().nextBoolean();
    }
}
