package org.loomdev.loom.plugin.data;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.plugin.PluginContainer;
import org.loomdev.api.plugin.PluginMetadata;
import org.loomdev.loom.plugin.loader.PluginClassLoader;

public class LoomPluginContainer implements PluginContainer {

    private final PluginMetadata pluginMetadata;
    private final PluginClassLoader classloader;
    private final Object instance;

    public LoomPluginContainer(@NotNull PluginMetadata pluginMetadata, @NotNull Object instance, @NotNull PluginClassLoader classloader) {
        this.pluginMetadata = pluginMetadata;
        this.instance = instance;
        this.classloader = classloader;
    }

    @Override
    public @NotNull PluginMetadata getMetadata() {
        return this.pluginMetadata;
    }

    @Override
    public @NotNull Object getInstance() {
        return this.instance;
    }

    @Override
    public @NotNull ClassLoader getClassLoader() {
        return this.classloader;
    }
}
