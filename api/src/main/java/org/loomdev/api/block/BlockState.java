package org.loomdev.api.block;

import org.jetbrains.annotations.NotNull;

public interface BlockState {

    /**
     * Gets the block state's block type.
     *
     * @return The type.
     */
    @NotNull
    BlockType getBlockType();

    /**
     * Gets whether the block state is air.
     * 
     * @return Whether the block state is air.
     */
    boolean isAir();

    /**
     * Gets whether the block state has a block entity.
     * 
     * @return Whether the block state has a block entity.
     */
    boolean hasBlockEntity();
}
