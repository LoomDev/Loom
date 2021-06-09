package org.loomdev.api.command;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.tree.CommandNode;
import org.loomdev.api.plugin.exception.IllegalPluginStateException;

public interface CommandManager {

    /**
     * Registers a command node on the server.
     *
     * @param node The command to register.
     * @throws IllegalPluginStateException when the plugin is not in the {@link org.loomdev.api.plugin.PluginState#ENABLING} or {@link org.loomdev.api.plugin.PluginState#ENABLED} state.
     */
    void registerCommand(@NotNull CommandNode node) throws IllegalPluginStateException;

    /**
     * Registers a basic command on the server.
     *
     * @param command The command to register.
     * @throws IllegalPluginStateException when the plugin is not in the {@link org.loomdev.api.plugin.PluginState#ENABLING} or {@link org.loomdev.api.plugin.PluginState#ENABLED} state.
     */
    default void registerCommand(@NotNull FlatCommand command) {
        registerCommand(command.convert());
    }
    
    /**
     * Unregisters all commands the plugin has registered.
     */
    void unregisterAll();
}
