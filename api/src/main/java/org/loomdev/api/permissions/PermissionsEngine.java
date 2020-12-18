package org.loomdev.api.permissions;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.plugin.PluginMetadata;

public interface PermissionsEngine {

    void registerPermissionsHandler(@NotNull PluginMetadata metadata, @NotNull PermissionHandler handler);

    void unregisterPermissionsHandler(@NotNull PluginMetadata metadata);

    @NotNull
    PermissionValue getPermission(@NotNull PermissionSubject subject, @NotNull String permission);

}
