package org.loomdev.api.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;

/**
 * Represents a reference to a block in a world.
 * Every value can change, apart from the location.
 */
public interface BlockPointer {

    /**
     * Gets the block's location.
     * This will always be the same.
     *
     * @return The location.
     */
    @NotNull
    Location getLocation();

    @NotNull
    BlockType getBlockType();

    void setBlockType(@NotNull BlockType type);

    void setBlockType(@NotNull BlockType type, @NotNull World.UpdateType updateType);

    void setBlockType(@NotNull BlockType type, int updatePriority);

    @NotNull
    BlockState getBlockState();

    void setBlockState(@NotNull BlockState state);

    void setBlockState(@NotNull BlockState state, @NotNull World.UpdateType updateType);
}
