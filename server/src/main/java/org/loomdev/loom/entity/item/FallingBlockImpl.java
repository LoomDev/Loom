package org.loomdev.loom.entity.item;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.item.FallingBlock;
import org.loomdev.loom.block.BlockStateImpl;
import org.loomdev.loom.entity.EntityImpl;

public class FallingBlockImpl extends EntityImpl implements FallingBlock {

    public FallingBlockImpl(net.minecraft.world.entity.item.FallingBlockEntity entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<FallingBlock> getType() {
        return EntityType.FALLING_BLOCK;
    }

    @NotNull
    @Override
    public net.minecraft.world.entity.item.FallingBlockEntity getMinecraftEntity() {
        return (net.minecraft.world.entity.item.FallingBlockEntity) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public BlockState getBlock() {
        return new BlockStateImpl(getMinecraftEntity().getBlockState());
    }

    @Override
    public void setBlock(@NotNull BlockState blockState) {
        getMinecraftEntity().blockState = ((BlockStateImpl) blockState).getMinecraftState();
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
