package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.event.Event;

/**
 * Represents an event that is related to a block in the world.
 */
public class BlockEvent implements Event {

    private final BlockPointer block;

    public BlockEvent(@NotNull BlockPointer block) {
        this.block = block;
    }

    @NotNull
    public BlockPointer getBlock() {
        return this.block;
    }
}
