package org.loomdev.api.plugin.metadata;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface PluginDependency {

    @NotNull
    String getId();

    @NotNull
    Optional<String> getVersion();

    boolean isRequired();
}
