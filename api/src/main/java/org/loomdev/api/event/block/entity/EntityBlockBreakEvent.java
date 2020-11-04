package org.loomdev.api.event.block.entity;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.block.BlockBreakEvent;

/**
 * Fired when a player destroys a block in the world.
 * The block will not break if this event is cancelled.
 */
public class EntityBlockBreakEvent extends BlockBreakEvent implements Cancellable {

    private final Entity entity;

    public EntityBlockBreakEvent(Block block, Entity entity) {
        super(block);
        this.entity = entity;
    }

    @NotNull
    public Entity getPlayer() {
        return entity;
    }
}
