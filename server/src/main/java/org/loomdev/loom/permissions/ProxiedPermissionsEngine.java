package org.loomdev.loom.permissions;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.permissions.PermissionHandler;
import org.loomdev.api.permissions.PermissionSubject;
import org.loomdev.api.permissions.PermissionValue;
import org.loomdev.api.permissions.PermissionsEngine;
import org.loomdev.api.plugin.PluginState;
import org.loomdev.api.plugin.exception.IllegalPluginStateException;
import org.loomdev.api.plugin.metadata.PluginMetadata;
import org.loomdev.loom.server.ServerImpl;

public class ProxiedPermissionsEngine implements PermissionsEngine {

    private final PluginMetadata metadata;
    private final ServerImpl internalServer;
    private final PermissionsEngineImpl internalPermissionsEngine;

    public ProxiedPermissionsEngine(PluginMetadata metadata, ServerImpl internalServer) {
        this.metadata = metadata;
        this.internalServer = internalServer;
        this.internalPermissionsEngine = internalServer.getPermissionsEngine();
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
    public void registerPermissionHandler(@NotNull PermissionHandler handler) throws IllegalPluginStateException {
        checkState();
        this.internalPermissionsEngine.registerPermissionsHandler(metadata, handler);
    }

    @Override
    public void unregisterPermissionsHandler() {
        this.internalPermissionsEngine.unregisterPermissionsHandler(metadata);
    }

    @Override
    public @NotNull PermissionValue getPermission(@NotNull PermissionSubject subject, @NotNull String permission) {
        return internalPermissionsEngine.getPermission(subject, permission);
    }
}
