package com.ftm.vcp.plugin.infra.db;

import com.ftm.vcp.plugin.infra.PluginRepo;
import com.ftm.vcp.plugin.infra.RepoType;
import org.springframework.stereotype.Component;

@Component
public final class DbRepo implements PluginRepo {
    @Override
    public String save(final Object entity) {
        logOperation("save", System.out::println);
        return "saved from db repo";
    }

    @Override
    public Object find(final String id) {
        logOperation("find", System.out::println);
        return new Object();
    }

    @Override
    public int delete(final String id) {
        logOperation("delete", System.out::println);
        return 2;
    }

    @Override
    public boolean supports(final RepoType repoType) {
        return repoType == RepoType.DB;
    }
}
