package org.loomdev.loom.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.block.BlockType;

public class BlockStateImpl implements BlockState {

    private final net.minecraft.world.level.block.state.BlockState mcBlockState;

    protected BlockStateImpl(@NotNull net.minecraft.world.level.block.state.BlockState mcBlockState) {
        this.mcBlockState = mcBlockState;
    }

    @Override
    public @NotNull BlockType getType() {
        return null;
    }

    @NotNull
    public net.minecraft.world.level.block.state.BlockState getMinecraftBlockState() {
        return mcBlockState;
    }

    public static BlockStateImpl of(@NotNull net.minecraft.world.level.block.state.BlockState mcBlockState) {
        return new BlockStateImpl(mcBlockState);
    }
}
