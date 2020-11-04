package org.loomdev.api.event.block.entity;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.block.BlockEvent;

/**
 * Fired when a player places a block in the world.
 * The block will not get placed if this event is cancelled.
 */
public class EntityBlockPlaceEvent extends BlockEvent implements Cancellable {

    private final Entity entity;
    private boolean cancelled;

    public EntityBlockPlaceEvent(Block block, Entity entity) {
        super(block);
        this.entity = entity;
    }

    @NotNull
    public Entity getPlayer() {
        return entity;
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
