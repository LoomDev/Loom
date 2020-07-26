package org.loomdev.loom.block;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.block.Material;
import org.loomdev.api.world.Chunk;
import org.loomdev.api.world.World;

public class BlockImpl implements Block {

    private final net.minecraft.block.BlockState mcBlock;
    private final WorldAccess world; // TODO maybe convert to loom world
    private final BlockPos pos;

    private BlockImpl(WorldAccess world, BlockPos pos) {
        this.world = world;
        this.pos = pos;
        this.mcBlock = world.getBlockState(pos);
    }

    public static BlockImpl at(WorldAccess world, BlockPos pos) {
        return new BlockImpl(world, pos);
    }

    public net.minecraft.block.BlockState getMinecraftBlock() {
        return this.mcBlock;
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
    public Material getMaterial() {
        return null;
    }

    @Override
    public Material setMaterial() {
        return null;
    }
}
