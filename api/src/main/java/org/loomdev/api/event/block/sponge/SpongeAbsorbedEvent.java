package org.loomdev.api.event.block.sponge;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.block.BlockEvent;

import java.util.Set;

/**
 * Fired when a sponge is placed in water and absorbs water blocks around it.
 * The water blocks will not be absorbed if this event is cancelled.
 */
public class SpongeAbsorbedEvent extends BlockEvent implements Cancellable {

    private final Set<Block> absorbedBlocks;
    private boolean cancelled;

    public SpongeAbsorbedEvent(@NotNull Block block, @NotNull Set<Block> absorbedBlocks) {
        super(block);
        this.absorbedBlocks = absorbedBlocks;
    }

    public @NotNull Set<Block> getAbsorbedBlocks() {
        return this.absorbedBlocks;
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
