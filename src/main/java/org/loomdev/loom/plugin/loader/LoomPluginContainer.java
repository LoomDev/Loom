package org.loomdev.loom.plugin.loader;

import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.plugin.PluginContainer;
import org.loomdev.api.plugin.PluginMetadata;

import java.util.Optional;

public class LoomPluginContainer implements PluginContainer {

    private final PluginMetadata pluginMetadata;
    private Plugin instance;

    public LoomPluginContainer(PluginMetadata pluginMetadata, Plugin instance) {
        this.pluginMetadata = pluginMetadata;
        this.instance = instance;
    }

    @Override
    public PluginMetadata getMetadata() {
        return this.pluginMetadata;
    }

    @Override
    public Optional<Plugin> getInstance() {
        return Optional.ofNullable(this.instance);
    }

    @Override
    public boolean isEnabled() {
        return getInstance().isPresent();
    }

    @Override
    public boolean isDisabled() {
        return !getInstance().isPresent();
    }

    public void setInstance(Plugin instance) {
        this.instance = instance;
    }
}
