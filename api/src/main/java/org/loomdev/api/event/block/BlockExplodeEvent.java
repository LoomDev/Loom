package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.event.Cancellable;

import java.util.Set;

/**
 * Fired when a block creates an explosion in the world.
 * An explosion will not be created and blocks will not be broken if this event is cancelled.
 */
public class BlockExplodeEvent extends BlockEvent implements Cancellable {

    private final Set<BlockPointer> affectedBlocks;
    private boolean cancelled;

    public BlockExplodeEvent(BlockPointer block, Set<BlockPointer> affectedBlocks) {
        super(block);
        this.affectedBlocks = affectedBlocks;
    }

    @NotNull
    public Set<BlockPointer> getAffectedBlocks() {
        return affectedBlocks;
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
