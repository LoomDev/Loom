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

    /**
     * Sends an error message to the command source.
     * This does not change formatting by default.
     *
     * @param message The message (as a legacy string).
     */
    default void sendError(@NotNull String message) {
        sendMessage(message);
    }

    /**
     * Sends an error message to the command source.
     * This does not change formatting by default.
     *
     * @param message The message (as a legacy string).
     * @param sender The sender's UUID (used for blocking players on the client).
     */
    default void sendError(@NotNull String message, @NotNull UUID sender) {
        sendMessage(message, sender);
    }

    /**
     * Sends an error message to the command source.
     * This does not change formatting by default.
     *
     * @param message The message (as a legacy string).
     */
    default void sendError(@NotNull Component message) {
        sendMessage(message);
    }

    /**
     * Sends an error message to the command source.
     * This does not change formatting by default.
     *
     * @param message The message (as a text component).
     * @param sender The sender's UUID (used for blocking players on the client).
     */
    default void sendError(@NotNull Component message, @NotNull UUID sender) {
        sendMessage(message, sender);
    }

    // TODO getName, broadcastToOps
}
