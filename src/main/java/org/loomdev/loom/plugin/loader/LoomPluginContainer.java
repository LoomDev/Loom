package org.loomdev.loom.plugin.loader;

import org.loomdev.api.plugin.PluginContainer;
import org.loomdev.api.plugin.PluginMetadata;

import java.util.Optional;

public class LoomPluginContainer implements PluginContainer {

    private final PluginMetadata pluginMetadata;
    private final Object instance;

    public LoomPluginContainer(PluginMetadata pluginMetadata, Object instance) {
        this.pluginMetadata = pluginMetadata;
        this.instance = instance;
    }

    @Override
    public PluginMetadata getMetadata() {
        return this.pluginMetadata;
    }

    @Override
    public Optional<?> getInstance() {
        return Optional.ofNullable(this.instance);
    }
}
