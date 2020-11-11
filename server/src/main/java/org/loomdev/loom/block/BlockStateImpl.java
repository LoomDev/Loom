package org.loomdev.loom.block;

import net.minecraft.core.Registry;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.block.BlockType;

public class BlockStateImpl implements BlockState {

    private final net.minecraft.world.level.block.state.BlockState mcState;

    public BlockStateImpl(@NotNull net.minecraft.world.level.block.state.BlockState mcBlockState) {
        this.mcState = mcBlockState;
    }

    @NotNull
    public net.minecraft.world.level.block.state.BlockState getMinecraftState() {
        return mcState;
    }

    @Override
    @NotNull
    public BlockType getBlockType() {
        var key = Registry.BLOCK.getKey(getMinecraftState().getBlock()).toString();
        return BlockType.getById(key);
    }

    @Override
    public boolean isAir() {
        return getMinecraftState().isAir();
    }

    @Override
    public boolean hasBlockEntity() {
        return getMinecraftState().hasBlockEntity();
    }
}
