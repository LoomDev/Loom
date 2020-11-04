package org.loomdev.api.event.player.connection;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.player.PlayerEvent;

/**
 * Fired when a player disconnects from the server.
 * This event cannot be cancelled.
 */
public class PlayerDisconnectEvent extends PlayerEvent {

    private Component message;

    public PlayerDisconnectEvent(Player player, Component message) {
        super(player);
        this.message = message;
    }

    @Nullable
    public Component getMessage() {
        return message;
    }

    public void setMessage(@NotNull Component message) {
        this.message = message;
    }
}
