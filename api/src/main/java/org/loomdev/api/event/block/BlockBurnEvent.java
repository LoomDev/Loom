package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.event.Cancellable;

/**
 * Fired when a flammable block in the world is destroyed by a fire block.
 * The block will not disappear if this event is cancelled.
 */
public class BlockBurnEvent extends BlockEvent implements Cancellable {

    private final BlockPointer source;
    private boolean cancelled;

    public BlockBurnEvent(BlockPointer block, BlockPointer source) {
        super(block);
        this.source = source;
    }

    @NotNull
    @Nullable
    public BlockPointer getSource() {
        return source;
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
