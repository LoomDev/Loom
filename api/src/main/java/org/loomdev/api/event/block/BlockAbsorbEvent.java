package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.event.Cancellable;

import java.util.Set;

/**
 * Fired when a fluid block is absorbed (removed from the world).
 * The water blocks will not be absorbed if this event is cancelled.
 */
public class BlockAbsorbEvent extends BlockEvent implements Cancellable {

    private final Set<BlockPointer> absorbedBlocks;
    private boolean cancelled;

    public BlockAbsorbEvent(BlockPointer block, Set<BlockPointer> absorbedBlocks) {
        super(block);
        this.absorbedBlocks = absorbedBlocks;
    }

    @NotNull
    public Set<BlockPointer> getAbsorbedBlocks() {
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
