package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.event.Cancellable;

import java.util.Set;

/**
 * Fired when a block creates an explosion in the world.
 * An explosion will not be created and blocks will not be broken if this event is cancelled.
 */
public class BlockExplodedEvent extends BlockEvent implements Cancellable {

    private final Set<Block> explodedBlocks;
    private boolean cancelled;

    public BlockExplodedEvent(@NotNull Block block, @NotNull Set<Block> explodedBlocks) {
        super(block);
        this.explodedBlocks = explodedBlocks;
    }

    public @NotNull Set<Block> getExplodedBlocks() {
        return explodedBlocks;
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
