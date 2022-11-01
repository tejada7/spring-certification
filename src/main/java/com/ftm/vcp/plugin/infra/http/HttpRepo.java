package com.ftm.vcp.plugin.infra.http;

import com.ftm.vcp.plugin.infra.PluginRepo;
import com.ftm.vcp.plugin.infra.RepoType;
import org.springframework.stereotype.Component;

@Component
public final class HttpRepo implements PluginRepo {

    @Override
    public String save(final Object entity) {
        logOperation("save", System.out::println);
        return "saved from http repo";
    }

    @Override
    public Object find(final String id) {
        logOperation("find", System.out::println);
        return new Object();
    }

    @Override
    public int delete(final String id) {
        logOperation("delete", System.out::println);
        return 1;
    }

    @Override
    public boolean supports(final RepoType repoType) {
        return repoType == RepoType.HTTP;
    }
}
