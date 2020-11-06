package org.loomdev.api.event.block;

import org.loomdev.api.block.Block;
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

    private boolean cancelled;

    public BlockChangeEvent(Block block) {
        super(block);
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
