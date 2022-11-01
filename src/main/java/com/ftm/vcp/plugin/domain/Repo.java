package com.ftm.vcp.plugin.domain;

import java.util.function.Consumer;

public interface Repo {
    Object find(final String id);

    String save(final Object entity);

    int delete(final String id);

    default void logOperation(final String operation, final Consumer<String> consumer) {
        consumer.accept(operation + " from " + getClass().getSimpleName());
    }
}
