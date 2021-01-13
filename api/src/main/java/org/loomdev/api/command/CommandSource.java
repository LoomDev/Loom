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
     * @param uuid The sender's UUID (used for blocking players on the client).
     */
    void sendMessage(@NotNull String message, @NotNull UUID uuid);

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
     * @param uuid The sender's UUID (used for blocking players on the client).
     */
    void sendMessage(@NotNull Component message, @NotNull UUID uuid);

    /**
     * Sends an error message to the command source.
     * Error messages appear in red.
     *
     * @param message The message (as a legacy string).
     */
    void sendError(@NotNull String message);

    /**
     * Sends an error message to the command source.
     * Error messages appear in red.
     *
     * @param message The message (as a legacy string).
     * @param uuid The sender's UUID (used for blocking players on the client).
     */
    void sendError(@NotNull String message, @NotNull UUID uuid);

    /**
     * Sends an error message to the command source.
     * Error messages appear in red.
     *
     * @param message The message (as a legacy string).
     */
    void sendError(@NotNull Component message);

    /**
     * Sends an error message to the command source.
     * Error messages appear in red.
     *
     * @param message The message (as a text component).
     * @param uuid The sender's UUID (used for blocking players on the client).
     */
    void sendError(@NotNull Component message, @NotNull UUID uuid);

    // TODO getName, broadcastToOps
}
