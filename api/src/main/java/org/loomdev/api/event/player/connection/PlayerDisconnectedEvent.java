package org.loomdev.api.event.player.connection;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.player.PlayerEvent;

import java.util.Optional;

;

/**
 * Fired when a player disconnects from the server.
 * This event cannot be cancelled.
 */
public class PlayerDisconnectedEvent extends PlayerEvent {

    private Component message;

    public PlayerDisconnectedEvent(@NotNull Player player, @Nullable Component message) {
        super(player);
        this.message = message;
    }

    public Optional<Component> getMessage() {
        return Optional.ofNullable(this.message);
    }

    public void setMessage(@NotNull Component message) {
        this.message = message;
    }
}
