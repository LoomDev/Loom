package org.loomdev.api.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.world.Chunk;
import org.loomdev.api.world.World;

/**
 * Represents a block location in a world. This is not an immutable snapshot of a block.
 */
public interface Block {

    /**
     * Returns the current BlockState of the block represented
     * by this class. The returned BlockState is a snapshot of the
     * current block and will not reflect any changes made to it
     * after the object has been returned.
     *
     * @return The current state of the block.
     */
    @NotNull BlockState getBlockState();

    int getX();

    int getY();

    int getZ();

    @NotNull Chunk getChunk();

    @NotNull World getWorld();

    @NotNull BlockType getType();

    void setType(@NotNull BlockType type);
}
