package org.loomdev.api.event.block.fluid;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.block.BlockEvent;

public class FluidLevelChangeEvent extends BlockEvent implements Cancellable {

    private final BlockState updatedState;
    private boolean cancelled;

    public FluidLevelChangeEvent(@NotNull Block block, @NotNull BlockState updatedState) {
        super(block);
        this.updatedState = updatedState;
    }

    public @NotNull BlockState getUpdatedState() {
        return this.updatedState;
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
