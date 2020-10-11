package org.loomdev.api.event.player;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Event;

public class PlayerEvent implements Event {

    private final Player player;

    public PlayerEvent(@NotNull Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
}
