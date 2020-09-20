package org.loomdev.loom.entity.vehicle;

import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.entity.vehicle.Minecart;
import org.loomdev.loom.block.BlockStateImpl;
import org.loomdev.loom.entity.EntityImpl;

public abstract class MinecartImpl extends EntityImpl implements Minecart {

    public MinecartImpl(AbstractMinecartEntity entity) {
        super(entity);
    }

    @NotNull
    @Override
    public AbstractMinecartEntity getMinecraftEntity() {
        return (AbstractMinecartEntity) super.getMinecraftEntity();
    }

    @Nullable
    @Override
    public BlockState getDisplayedBlock() {
        return BlockStateImpl.of(getMinecraftEntity().getContainedBlock());
    }

    @Override
    public void setDisplayedBlock(@Nullable BlockState blockState) {
        getMinecraftEntity().setCustomBlock(blockState == null ? null : ((BlockStateImpl) blockState).getMinecraftBlockState());
    }

    @Override
    public int getDisplayedBlockOffset() {
        return getMinecraftEntity().getBlockOffset();
    }

    @Override
    public void setDisplayedBlockOffset(int offset) {
        getMinecraftEntity().setCustomBlockOffset(offset);
    }

}
