package org.loomdev.loom.entity.item;

import net.minecraft.world.entity.item.FallingBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.FallingBlock;
import org.loomdev.loom.block.BlockStateImpl;
import org.loomdev.loom.entity.EntityImpl;

public class FallingBlockImpl extends EntityImpl implements FallingBlock {

    public FallingBlockImpl(FallingBlockEntity entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<FallingBlock> getType() {
        return EntityType.FALLING_BLOCK;
    }

    @NotNull
    @Override
    public FallingBlockEntity getMinecraftEntity() {
        return (FallingBlockEntity) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public BlockState getBlock() {
        return BlockStateImpl.of(getMinecraftEntity().getBlockState());
    }

    @Override
    public void setBlock(@NotNull BlockState blockState) {
        getMinecraftEntity().blockState = ((BlockStateImpl) blockState).getMinecraftBlockState();
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
