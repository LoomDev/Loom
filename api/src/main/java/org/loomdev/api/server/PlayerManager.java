package org.loomdev.api.server;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface PlayerManager {

    /**
     * Returns all currently online players on the server.
     *
     * @return Immutable collection of all online players.
     */
    @NotNull
    Collection<? extends Player> getOnlinePlayers();

    /**
     * Send a message to all players on the server.
     *
     * @param message The message to send.
     */
    default void broadcastMessage(@NotNull String message) {
        broadcastMessage(Component.text(message));
    }

    /**
     * Send a message to all players on the server.
     *
     * @param component The component to send.
     */
    void broadcastMessage(@NotNull Component component);

    /**
     * Get the amount of players online on the server.
     *
     * @return the amount of players online.
     */
    int getOnlinePlayerCount();

    /**
     * Get a player based on their UUID
     *
     * @param uuid The UUID of the player.
     * @return Instance of {@link Player} or {@code null} if the player is offline.
     */
    @NotNull
    Optional<Player> getPlayer(@NotNull UUID uuid);

    /**
     * Get a player based on their current username.
     *
     * @param username The username of the player.
     * @return Instance of {@link Player} or {@code null} if the player is offline.
     */
    @NotNull
    Optional<Player> getPlayer(@NotNull String username);

    /**
     * Check whether a {@link Player} is an operator.
     *
     * @param player The {@link Player} to check for.
     * @return True if the player is an operator, otherwise False.
     */
    boolean isOperator(@NotNull Player player);
}
