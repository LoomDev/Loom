package org.loomdev.api.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;

/**
 * Represents a block in a world.
 */
public interface BlockPointer {

    /**
     * Gets the block's location.
     *
     * @return The location.
     */
    @NotNull
    Location getLocation();

    /**
     * Gets the block's type.
     *
     * @return The type.
     */
    @NotNull
    BlockType getBlockType();

    /**
     * Sets the block's type.
     *
     * @param type The type.
     */
    void setBlockType(@NotNull BlockType type);

    /**
     * Sets the block's type.
     *
     * @param type The type.
     * @param updateType The update type.
     */
    void setBlockType(@NotNull BlockType type, @NotNull World.UpdateType updateType);

    /**
     * Sets the block's type.
     *
     * @param type The type.
     * @param updatePriority The update priority.
     */
    void setBlockType(@NotNull BlockType type, int updatePriority);

    /**
     * Gets the block's state.
     *
     * @return The state.
     */
    @NotNull
    BlockState getBlockState();

    /**
     * Sets the block's state.
     *
     * @param state The state.
     */
    void setBlockState(@NotNull BlockState state);

    /**
     * Sets the block's state.
     *
     * @param state The state.
     * @param updateType The update type.
     */
    void setBlockState(@NotNull BlockState state, @NotNull World.UpdateType updateType);
}
