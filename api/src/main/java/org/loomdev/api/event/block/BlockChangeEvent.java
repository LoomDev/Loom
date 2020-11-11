package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.event.Cancellable;

/**
 * Fired when a block changes state due to being exposed to the surrounding environment.
 * The block's state will not change if this event is cancelled.
 *
 * This event is fired when:
 * <ul>
 * <li>An unwaxed copper block oxidizes, changing state over time.</li>
 * </ul>
 */
public class BlockChangeEvent extends BlockEvent implements Cancellable {

    private final BlockState newState;
    private boolean cancelled;

    public BlockChangeEvent(BlockPointer block, BlockState newState) {
        super(block);
        this.newState = newState;
    }

    @NotNull
    public BlockState getNewState() {
        return newState;
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
