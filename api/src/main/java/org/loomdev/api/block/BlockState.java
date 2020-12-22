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
     * Gets if the block state is air.
     * 
     * @return If the block state is air.
     */
    boolean isAir();

    /**
     * Gets if the block state has a block entity.
     * 
     * @return If the block state has a block entity.
     */
    boolean hasBlockEntity();
}
