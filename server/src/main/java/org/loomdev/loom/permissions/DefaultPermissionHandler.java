package org.loomdev.loom.permissions;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.ConsoleCommandSource;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.permissions.PermissionHandler;
import org.loomdev.api.permissions.PermissionSubject;
import org.loomdev.api.permissions.PermissionValue;

import java.util.Map;

public class DefaultPermissionHandler implements PermissionHandler {

    /**
     * Key: permission
     * Value: true if can use the command anybody, false if operator only
     */
    private final Map<String, Boolean> defaultPermissions = Maps.newHashMap();

    public DefaultPermissionHandler() {
        // All default minecraft and loom permissions should be listed here

        defaultPermissions.put("loom.command.plugins", true);
        defaultPermissions.put("loom.command.plugins.admin", false);
    }

    @NotNull
    @Override
    public PermissionValue getPermission(@NotNull PermissionSubject subject, @NotNull String permission) {
        if (!defaultPermissions.containsKey(permission)) {
            return PermissionValue.UNKNOWN;
        }

        var hasPermission = defaultPermissions.get(permission);
        return hasPermission ? PermissionValue.PERMITTED : PermissionValue.DENIED;
    }

}
