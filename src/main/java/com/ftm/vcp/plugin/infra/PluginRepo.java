package com.ftm.vcp.plugin.infra;

import com.ftm.vcp.plugin.domain.Repo;
import org.springframework.plugin.core.Plugin;

public interface PluginRepo extends Repo, Plugin<RepoType> {
}
