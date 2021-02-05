package org.loomdev.loom.command;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandManager;
import org.loomdev.api.plugin.PluginState;
import org.loomdev.api.plugin.exception.IllegalPluginStateException;
import org.loomdev.api.plugin.metadata.PluginMetadata;
import org.loomdev.loom.server.ServerImpl;

public class ProxiedCommandManager implements CommandManager {

    private final PluginMetadata metadata;
    private final ServerImpl internalServer;
    private final CommandManagerImpl internalCommandManager;

    public ProxiedCommandManager(PluginMetadata metadata, ServerImpl internalServer) {
        this.metadata = metadata;
        this.internalServer = internalServer;
        this.internalCommandManager = internalServer.getCommandManager();
    }

    private void checkState() {
        var optionalContainer = internalServer.getPluginManager().getPlugins().getContainer(metadata.getId());
        if (optionalContainer.isEmpty()) {
            throw new IllegalPluginStateException(metadata.getId());
        }

        var container = optionalContainer.get();
        var state = container.getState();
        if (state != PluginState.ENABLING && state != PluginState.ENABLED) {
            throw new IllegalPluginStateException(metadata.getId());
        }
    }

    @Override
    public void registerCommand(@NotNull Command command) throws IllegalPluginStateException {
        checkState();
        this.internalCommandManager.register(metadata.getId(), command);
    }

    @Override
    public void unregisterAll() {
        this.internalCommandManager.unregister(metadata);
    }
}
