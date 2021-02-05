package org.loomdev.loom.permissions;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.command.ConsoleCommandSource;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.permissions.PermissionHandler;
import org.loomdev.api.permissions.PermissionSubject;
import org.loomdev.api.permissions.PermissionValue;
import org.loomdev.api.plugin.metadata.PluginMetadata;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.server.ServerImpl;

import java.util.HashMap;

public class PermissionsEngineImpl {

    private final DefaultPermissionHandler defaultPermissionHandler;
    private final HashMap<PluginMetadata, PermissionHandler> permissionHandlers;
    private final ServerImpl server;

    public PermissionsEngineImpl(ServerImpl server) {
        this.server = server;
        this.defaultPermissionHandler = new DefaultPermissionHandler();
        this.permissionHandlers = Maps.newHashMap();
    }

    public void registerPermissionsHandler(@NotNull PluginMetadata metadata, @NotNull PermissionHandler handler) {
        if (permissionHandlers.containsKey(metadata)) {
            return; // TODO throw error?
        }
        permissionHandlers.put(metadata, handler);
    }

    public void unregisterPermissionsHandler(@NotNull PluginMetadata metadata) {
        permissionHandlers.remove(metadata);
    }

    @NotNull
    public PermissionValue getPermission(@NotNull PermissionSubject subject, @NotNull String permission) {
        if (subject instanceof ConsoleCommandSource) {
            return PermissionValue.PERMITTED;
        }

        if (!(subject instanceof CommandSource)) {
            return calculatePermission(subject, permission);
        }

        var commandSource = (CommandSource) subject;
        if (commandSource.isOperator()) {
            return PermissionValue.PERMITTED;
        }

        return calculatePermission(subject, permission);
    }

    @NotNull
    private PermissionValue calculatePermission(@NotNull PermissionSubject subject, @NotNull String permission) {
        var calculatedPermissionValue = PermissionValue.UNKNOWN;

        for (PermissionHandler value : this.permissionHandlers.values()) {
            var permissionValue = value.getPermission(subject, permission);

            switch (permissionValue) {
                case UNKNOWN:
                    continue;
                case DENIED:
                    if (calculatedPermissionValue == PermissionValue.UNKNOWN) {
                        calculatedPermissionValue = PermissionValue.DENIED;
                    }
                    continue;
                case PERMITTED:
                    calculatedPermissionValue = PermissionValue.PERMITTED;
            }
        }

        if (calculatedPermissionValue == PermissionValue.UNKNOWN) {
            return this.defaultPermissionHandler.getPermission(subject, permission);
        }

        return calculatedPermissionValue;
    }

    public boolean isOperator(Player player) {
        var gameProfile = ((PlayerImpl) player).getMinecraftEntity().getGameProfile();
        return server.getMinecraftServer().getPlayerList().isOp(gameProfile);
    }
}
