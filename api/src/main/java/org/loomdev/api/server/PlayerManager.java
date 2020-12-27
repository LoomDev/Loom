package org.loomdev.api.server;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface PlayerManager {

    /**
     * Returns all currently online players on the server.
     *
     * @return Immutable collection of all online players.
     */
    @NotNull
    Stream<Player> getOnlinePlayers();

    /**
     * Sends a message to all players on the server.
     *
     * @param message The message to send.
     */
    default void broadcastMessage(@NotNull String message) {
        broadcastMessage(Component.text(message));
    }

    /**
     * Sends a message to all players on the server.
     *
     * @param component The component to send.
     */
    void broadcastMessage(@NotNull Component component);

    /**
     * Gets the amount of players online on the server.
     *
     * @return The amount of players online.
     */
    int getOnlinePlayerCount();

    /**
     * Gets a player based on their UUID
     *
     * @param uuid The UUID of the player.
     * @return Instance of {@link Player} or {@code null} if the player is offline.
     */
    @NotNull
    Optional<Player> getPlayer(@NotNull UUID uuid);

    /**
     * Gets a player based on their current username.
     *
     * @param username The username of the player.
     * @return Instance of player or {@code null} if the player is offline.
     */
    @NotNull
    Optional<Player> getPlayer(@NotNull String username);

    /**
     * Checks whether a player is an operator.
     *
     * @param player The player to check.
     * @return {@code true} if the player is an operator, otherwise {@code false}.
     */
    boolean isOperator(@NotNull Player player);
}
