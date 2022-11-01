package com.ftm.vcp.plugin.domain;

public class Port {
    private final Repo repo;

    public Port(final Repo repo) {
        this.repo = repo;
    }

    public Object find(final String id) {
        return repo.find(id);
    }

    public Object save(final Object entity) {
        return repo.save(entity);
    }

    public int delete(final String id) {
        return repo.delete(id);
    }
}
