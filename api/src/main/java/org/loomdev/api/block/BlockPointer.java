package org.loomdev.api.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;

public interface BlockPointer {

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
