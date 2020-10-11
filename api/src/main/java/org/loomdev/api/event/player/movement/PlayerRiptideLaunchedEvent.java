package org.loomdev.api.event.player.movement;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.player.PlayerEvent;

/**
 * Fired when a player uses a Trident with the Riptide enchantment and launches themselves into the air.
 * The player will not be launched if this event is cancelled.
 */
public class PlayerRiptideLaunchedEvent extends PlayerEvent implements Cancellable {

    private int riptideLevel;
    private boolean cancelled;

    public PlayerRiptideLaunchedEvent(@NotNull Player player, int riptideLevel) {
        super(player);
        this.riptideLevel = riptideLevel;
    }

    public int getRiptideLevel() {
        return riptideLevel;
    }

    public void setRiptideLevel(int riptideLevel) { // TODO impl doesn't seem to do anything
        this.riptideLevel = riptideLevel;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
