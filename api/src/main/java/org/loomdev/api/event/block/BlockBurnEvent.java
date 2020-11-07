package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.block.Block;
import org.loomdev.api.event.Cancellable;

import java.util.Optional;

/**
 * Fired when a flammable block in the world is destroyed by a fire block.
 * The block will not disappear if this event is cancelled.
 */
public class BlockBurnEvent extends BlockEvent implements Cancellable {

    private final Block source;
    private boolean cancelled;

    public BlockBurnEvent(Block block, Block source) {
        super(block);
        this.source = source;
    }

    @NotNull
    @Nullable
    public Block getSource() {
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
