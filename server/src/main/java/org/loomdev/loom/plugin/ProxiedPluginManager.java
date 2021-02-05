package org.loomdev.loom.plugin;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.plugin.metadata.PluginMetadata;
import org.loomdev.loom.server.ServerImpl;

import java.util.stream.Stream;

public class ProxiedPluginManager implements PluginManager {

    private final PluginMetadata metadata;
    private final InternalPluginManager internalPluginManager;

    public ProxiedPluginManager(PluginMetadata metadata, @NotNull ServerImpl internalServer) {
        this.metadata = metadata;
        this.internalPluginManager = internalServer.getPluginManager();
    }

    @Override
    public Stream<PluginMetadata> getPlugins() {
        return internalPluginManager.getPlugins().getAllMetadata();
    }
}
