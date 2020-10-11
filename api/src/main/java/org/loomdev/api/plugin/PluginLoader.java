package org.loomdev.api.plugin;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Optional;

public interface PluginLoader {

    @NotNull Optional<PluginMetadata> loadMetadata(@NotNull Path source);

    @NotNull Optional<PluginContainer> loadPlugin(@NotNull PluginMetadata plugin);

}
