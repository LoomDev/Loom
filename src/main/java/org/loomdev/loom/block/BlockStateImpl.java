package org.loomdev.loom.block;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.Block;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.block.Material;

public class BlockStateImpl implements BlockState {

    private final Block mcBlock;
    private final net.minecraft.block.BlockState mcState;

    protected BlockStateImpl(@NotNull Block block) {
        this.mcBlock = block;
        this.mcState = ((BlockImpl) block).getMinecraftBlock().getBlock().getDefaultState();
    }

    public static BlockStateImpl of(@NotNull Block block) {
        return new BlockStateImpl(block);
    }

    @Override
    public @NotNull Block getBlock() {
        return mcBlock;
    }

    @Override
    public @NotNull Material getType() {
        return null;
    }
}
