package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.block.BlockEvent;

import java.util.Set;

/**
 * Fired when a fluid block is absorbed (removed from the world).
 * The water blocks will not be absorbed if this event is cancelled.
 */
public class BlockAbsorbEvent extends BlockEvent implements Cancellable {

    private final Set<Block> absorbedBlocks;
    private boolean cancelled;

    public BlockAbsorbEvent(Block block, Set<Block> absorbedBlocks) {
        super(block);
        this.absorbedBlocks = absorbedBlocks;
    }

    @NotNull
    public Set<Block> getAbsorbedBlocks() {
        return absorbedBlocks;
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
