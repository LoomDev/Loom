package org.loomdev.api.block;

import org.jetbrains.annotations.NotNull;

public interface BlockState {

    @NotNull
    BlockType getBlockType();

    boolean isAir();

    /**
     * Gets whether the block state has a block entity (contains extra data, such as a chest or sign).
     * 
     * @return Whether the block state has a block entity.
     */
    boolean hasBlockEntity();
}
