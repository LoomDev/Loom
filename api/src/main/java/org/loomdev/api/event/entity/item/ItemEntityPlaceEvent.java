package org.loomdev.api.event.entity.item;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.block.Block;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.entity.EntityEvent;

/**
 * Fires when an item entity, such as an armor stand, has been placed in the world.
 * The armor stand will not be placed if this event is cancelled.
 */
public class ItemEntityPlaceEvent extends EntityEvent implements Cancellable {

    private Player player;
    private Block dispenser; // TODO change to dispenser block
    private final Cause cause;
    private boolean cancelled;

    public ItemEntityPlaceEvent(Entity entity, Player player) {
        super(entity);
        this.player = player;
        this.cause = Cause.PLAYER;
    }

    public ItemEntityPlaceEvent(Entity entity, Block dispenser) { // TODO change to dispenser block
        super(entity);
        this.dispenser = dispenser;
        this.cause = Cause.DISPENSER;
    }

    @Nullable
    public Player getPlayer() { // TODO two different events possibly
        return player;
    }

    @Nullable
    public Block getDispenser() { // TODO change to dispenser block
        return dispenser;
    }

    @NotNull
    public Cause getCause() {
        return cause;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Represents whether a dispenser or
     * player placed the armor stand.
     */
    public enum Cause {

        PLAYER,
        DISPENSER
    }
}
