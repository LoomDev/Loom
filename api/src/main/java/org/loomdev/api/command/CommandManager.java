package org.loomdev.api.command;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.plugin.PluginMetadata;

public interface CommandManager {

    void register(@NotNull Plugin plugin, @NotNull Command command);

    void register(@NotNull PluginMetadata metadata, @NotNull Command command);

    void unregister(@NotNull Plugin plugin);

    void unregister(@NotNull PluginMetadata metadata);
}
