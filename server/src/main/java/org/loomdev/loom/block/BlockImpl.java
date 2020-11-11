package org.loomdev.loom.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.world.Chunk;
import org.loomdev.api.world.World;

public class BlockImpl implements Block {

    private final net.minecraft.world.level.block.state.BlockState mcBlock;
    private final Level level; // TODO maybe convert to loom world
    private final BlockPos pos;

    private BlockImpl(Level world, BlockPos pos) {
        this.level = world;
        this.pos = pos;
        this.mcBlock = world.getBlockState(pos);
    }

    public static BlockImpl at(Level world, BlockPos pos) {
        return new BlockImpl(world, pos);
    }

    public net.minecraft.world.level.block.state.BlockState getMinecraftBlockState() {
        return this.mcBlock;
    }

    public Level getMinecraftWorld() {
        return level;
    }

    public BlockPos getMinecraftPos() {
        return pos;
    }

    // @Override TODO ffs, tech
    public @NotNull BlockState getBlockState() {
        return null;
    }

    @Override
    public int getX() {
        return this.pos.getX();
    }

    @Override
    public int getY() {
        return this.pos.getY();
    }

    @Override
    public int getZ() {
        return this.pos.getZ();
    }

    @Override
    public Chunk getChunk() {
        return null;
    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    @NotNull
    public BlockType getType() {
        System.out.println(getMinecraftBlockState().getBlock().getDescriptionId());
        return BlockType.getById(getMinecraftBlockState().getBlock().getDescriptionId());
    }

    @Override
    public void setType(@NotNull BlockType blockType) {

    }
}
