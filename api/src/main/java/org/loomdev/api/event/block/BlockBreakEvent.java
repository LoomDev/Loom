package org.loomdev.api.event.block;

import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.event.Cancellable;

/**
 * Fired when a player destroys a block in the world.
 * The block will not break if this event is cancelled.
 */
public class BlockBreakEvent extends BlockEvent implements Cancellable {

    private boolean dropsItems;
    private boolean cancelled;

    public BlockBreakEvent(BlockPointer block) {
        super(block);
    }

    public boolean dropsItems() {
        return this.dropsItems;
    }

    public void setDropsItems(boolean dropsItems) {
        this.dropsItems = dropsItems;
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
