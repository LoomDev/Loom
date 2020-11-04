package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.event.Cancellable;

import java.util.Set;

/**
 * Fired when a block creates an explosion in the world.
 * An explosion will not be created and blocks will not be broken if this event is cancelled.
 */
public class BlockExplodeEvent extends BlockEvent implements Cancellable {

    private final Set<Block> affectedBlocks;
    private boolean cancelled;

    public BlockExplodeEvent(Block block, Set<Block> affectedBlocks) {
        super(block);
        this.affectedBlocks = affectedBlocks;
    }

    @NotNull
    public Set<Block> getAffectedBlocks() {
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
