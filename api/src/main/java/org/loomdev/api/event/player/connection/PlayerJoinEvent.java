package org.loomdev.api.event.player.connection;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.player.PlayerEvent;

import java.util.Optional;

/**
 * Fired when a player joins the server and is about to log into the world.
 * This event cannot be cancelled.
 */
public class PlayerJoinEvent extends PlayerEvent {

    private Component message;

    public PlayerJoinEvent(Player player, Component message) {
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
