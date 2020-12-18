package org.loomdev.api.command;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.permissions.PermissionSubject;

public interface CommandSource extends CommandSourceConsumable, PermissionSubject {

    void sendMessage(@NotNull String message);

    void sendMessage(@NotNull Component message);
}
