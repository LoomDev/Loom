package org.loomdev.api.command;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.plugin.exception.IllegalPluginStateException;
import org.loomdev.api.plugin.metadata.PluginMetadata;

public interface CommandManager {

    /**
     * Registers a command on the server.
     *
     * @param command The command to register.
     * @throws IllegalPluginStateException when the plugin is not in the {@link org.loomdev.api.plugin.PluginState#ENABLING} or {@link org.loomdev.api.plugin.PluginState#ENABLED} state.
     */
    void registerCommand(@NotNull Command command) throws IllegalPluginStateException;

    /**
     * Unregisters all commands a plugin has registered.
     */
    void unregisterAll();
}
