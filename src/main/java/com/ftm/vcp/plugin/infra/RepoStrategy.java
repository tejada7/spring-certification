package com.ftm.vcp.plugin.infra;

import com.ftm.vcp.plugin.domain.Repo;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

@Component
public final class RepoStrategy implements Repo {

    private final PluginRegistry<PluginRepo, RepoType> plugins;

    public RepoStrategy(final PluginRegistry<PluginRepo, RepoType> plugins) {
        this.plugins = plugins;
    }

    @Override
    public String save(final Object entity) {
        plugins.getPluginFor(RepoType.HTTP).ifPresent(plugin -> plugin.save(entity));
        return plugins.getPluginFor(RepoType.DB)
                .map(http -> http.save(entity))
                .orElseThrow();
    }

    @Override
    public Object find(final String id) {
        return plugins.getPluginFor(RepoType.DB)
                .map(pluginRepo -> pluginRepo.find(id))
                .orElse(null);
    }

    @Override
    public int delete(final String id) {
        return plugins.getPluginFor(RepoType.DB)
                .map(pluginRepo -> pluginRepo.delete(id))
                .orElse(0);
    }
}
