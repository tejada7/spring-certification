package com.ftm.vcp.events;

public record Message(String value, boolean shouldBeConsumed) {

    @Override
    public String toString() {
        return value;
    }
}
