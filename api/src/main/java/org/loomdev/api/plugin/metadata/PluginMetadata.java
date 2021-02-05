package org.loomdev.api.plugin.metadata;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.ApiVersion;
import org.loomdev.api.plugin.Dependency;
import org.loomdev.api.plugin.Plugin;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public interface PluginMetadata {

    @NotNull
    String getId();

    @NotNull
    String getName();

    @NotNull
    String getMainClass();

    @NotNull
    String getVersion();

    @NotNull
    Optional<String> getDescription();

    @NotNull
    String[] getAuthors();

    @NotNull
    PluginDependency[] getDependencies();

    @NotNull
    ApiVersion getMinimumApiVersion();
}
