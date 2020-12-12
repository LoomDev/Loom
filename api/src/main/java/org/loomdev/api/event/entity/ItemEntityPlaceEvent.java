package org.loomdev.api.event.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Event;

/**
 * Fires when an item entity, such as an armor stand, has been placed in the world.
 * The armor stand will not be placed if this event is cancelled.
 */
public class ItemEntityPlaceEvent extends Event {

    private final Entity entity;
    private Player player;
    private BlockPointer dispenser; // TODO change to dispenser block
    private final Cause cause;
    private boolean cancelled;

    public ItemEntityPlaceEvent(Entity entity, Player player) {
        this.entity = entity;
        this.player = player;
        this.cause = Cause.PLAYER;
    }

    public ItemEntityPlaceEvent(Entity entity, BlockPointer dispenser) { // TODO change to dispenser block
        this.entity = entity;
        this.dispenser = dispenser;
        this.cause = Cause.DISPENSER;
    }

    @NotNull
    public Entity getEntity() {
        return entity;
    }

    @Nullable
    public Player getPlayer() { // TODO two different events possibly
        return player;
    }

    @Nullable
    public BlockPointer getDispenser() { // TODO change to dispenser block
        return dispenser;
    }

    @NotNull
    public Cause getCause() {
        return cause;
    }

    @Override
    public boolean isCancelable() {
        return true;
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
