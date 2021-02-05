package org.loomdev.loom.plugin.metadata;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.plugin.metadata.PluginDependency;

import java.util.Optional;

public class PluginDependencyImpl implements PluginDependency {

    private final String id;
    private final String version;
    private final boolean isRequired;

    public PluginDependencyImpl(@NotNull String id, @Nullable String version, boolean isRequired) {
        this.id = id;
        this.version = version;
        this.isRequired = isRequired;
    }

    @Override
    @NotNull
    public String getId() {
        return this.id;
    }

    @Override
    @NotNull
    public Optional<String> getVersion() {
        return Optional.ofNullable(this.version);
    }

    @Override
    public boolean isRequired() {
        return this.isRequired;
    }
}
