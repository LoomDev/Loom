package org.loomdev.loom.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockPointer;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.world.Chunk;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;

public class BlockPointerImpl implements BlockPointer {

    private final Level level;
    private final BlockPos blockPos;
    private final Location location;

    public BlockPointerImpl(Level level, BlockPos blockPos) {
        this.level = level;
        this.blockPos = blockPos;
        this.location = new Location(level.getLoomWorld(), blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    @NotNull
    public Level getMinecraftWorld() {
        return level;
    }

    @NotNull
    public BlockPos getMinecraftPos() {
        return blockPos;
    }

    @Override
    @NotNull
    public Location getLocation() {
        return location;
    }

    @Override
    @NotNull
    public BlockType getBlockType() {
        return getBlockState().getBlockType();
    }

    @Override
    public void setBlockType(@NotNull BlockType type) {
        setBlockType(type, World.UpdateType.NOTIFY); // TODO update to whatever is full update
    }

    @Override
    public void setBlockType(@NotNull BlockType type, @NotNull World.UpdateType updateType) {
        setBlockType(type, 3); // TODO convert updateType to integer form
    }

    @Override
    public void setBlockType(@NotNull BlockType type, int updatePriority) {
        var block = Registry.BLOCK.get(new ResourceLocation(type.getKey().toString()));
        getMinecraftWorld().setBlock(getMinecraftPos(), block.defaultBlockState(), updatePriority);
    }

    @Override
    @NotNull
    public BlockState getBlockState() {
        return new BlockStateImpl(getMinecraftWorld().getBlockState(getMinecraftPos()));
    }

    @Override
    public void setBlockState(@NotNull BlockState state) {
        setBlockState(state, World.UpdateType.NOTIFY); // TODO update to whatever is full update
    }

    @Override
    public void setBlockState(@NotNull BlockState state, @NotNull World.UpdateType updateType) {
        getMinecraftWorld().setBlock(getMinecraftPos(), ((BlockStateImpl) state).getMinecraftState(), 3); // TODO convert updateType to integer form
    }
}
