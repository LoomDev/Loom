package org.loomdev.loom.entity;

import net.minecraft.entity.FallingBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.FallingBlock;
import org.loomdev.loom.block.BlockStateImpl;

public class FallingBlockImpl extends EntityImpl implements FallingBlock {

    public FallingBlockImpl(FallingBlockEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<FallingBlock> getType() {
        return EntityType.FALLING_BLOCK;
    }

    @NotNull
    @Override
    public FallingBlockEntity getMinecraftEntity() {
        return (FallingBlockEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull BlockState getBlock() {
        return BlockStateImpl.of(getMinecraftEntity().getBlockState());
    }

    @Override
    public void setBlock(@NotNull BlockState blockState) {
        getMinecraftEntity().block = ((BlockStateImpl) blockState).getMinecraftBlockState();
    }

    @Override
    public boolean getDropsItem() {
        return getMinecraftEntity().dropItem;
    }

    @Override
    public void setDropsItem(boolean flag) {
        getMinecraftEntity().dropItem = flag;
    }

}
