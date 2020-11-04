package org.loomdev.api.event.player.movement;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.player.PlayerEvent;

/**
 * Fired when a player enters flight mode whilst wearing an Elytra.
 * The player will not enter flight mode if this event is cancelled.
 */
// TODO this one is not updated because we need to distinguish elytra flight versus normal flight mode
public class PlayerEnteredFlightEvent extends PlayerEvent implements Cancellable {
    private boolean cancelled;

    public PlayerEnteredFlightEvent(@NotNull Player player) {
        super(player);
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
