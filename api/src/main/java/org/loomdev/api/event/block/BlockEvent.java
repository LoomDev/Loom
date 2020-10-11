package org.loomdev.api.event.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.event.Event;

/**
 * Represents an event that is related to a block in the world.
 */
public class BlockEvent implements Event {

    private final Block block;

    public BlockEvent(@NotNull Block block) {
        this.block = block;
    }

    public @NotNull Block getBlock() {
        return this.block;
    }
}
