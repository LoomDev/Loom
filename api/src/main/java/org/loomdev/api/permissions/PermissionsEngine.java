package org.loomdev.api.permissions;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.plugin.exception.IllegalPluginStateException;

public interface PermissionsEngine {

    /**
     * Registers a {@link PermissionHandler} for the current plugin.
     *
     * <p>Note: Only one {@link PermissionHandler} can be registered per plugin.</p>
     *
     * @param handler The {@link PermissionHandler} to register
     * @throws IllegalPluginStateException when the plugin is not in the {@link org.loomdev.api.plugin.PluginState#ENABLING} or {@link org.loomdev.api.plugin.PluginState#ENABLED} state.
     */
    void registerPermissionHandler(@NotNull PermissionHandler handler) throws IllegalPluginStateException;

    /**
     * Unregisters the {@link PermissionHandler}, if one is registered for the plugin.
     */
    void unregisterPermissionsHandler();

    @NotNull
    PermissionValue getPermission(@NotNull PermissionSubject subject, @NotNull String permission);

}
