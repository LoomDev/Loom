package org.loomdev.loom.plugin.data;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.plugin.PluginContainer;
import org.loomdev.api.plugin.PluginMetadata;
import org.loomdev.loom.plugin.loader.PluginClassLoader;

import java.util.Optional;

public class LoomPluginContainer implements PluginContainer {

    private final PluginMetadata pluginMetadata;
    private final PluginClassLoader classloader;
    private Plugin instance;

    public LoomPluginContainer(@NonNull PluginMetadata pluginMetadata, @Nullable Plugin instance, @NonNull PluginClassLoader classloader) {
        this.pluginMetadata = pluginMetadata;
        this.instance = instance;
        this.classloader = classloader;
    }

    @Override
    public @NonNull PluginMetadata getMetadata() {
        return this.pluginMetadata;
    }

    @Override
    public @NonNull Optional<Plugin> getInstance() {
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

    @Override
    public @NonNull ClassLoader getClassLoader() {
        return this.classloader;
    }
}
