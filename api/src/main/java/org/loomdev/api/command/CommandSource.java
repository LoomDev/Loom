package org.loomdev.api.command;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.permissions.PermissionSubject;

import java.util.UUID;

/**
 * Represents anything that can execute commands and receive messages.
 */
public interface CommandSource extends CommandSourceConsumable, PermissionSubject {

    /**
     * Sends a message to the command source.
     *
     * @param message The message (as a legacy string).
     */
    void sendMessage(@NotNull String message);

    /**
     * Sends a message to the command source.
     *
     * @param message The message (as a legacy string).
     * @param sender The sender's UUID (used for blocking players on the client).
     */
    void sendMessage(@NotNull String message, @NotNull UUID sender);

    /**
     * Sends a message to the command source.
     *
     * @param message The message (as a text component).
     */
    void sendMessage(@NotNull Component message);

    /**
     * Sends a message to the command source.
     *
     * @param message The message (as a text component).
     * @param sender The sender's UUID (used for blocking players on the client).
     */
    void sendMessage(@NotNull Component message, @NotNull UUID sender);

    // TODO getName, broadcastToOps
}
