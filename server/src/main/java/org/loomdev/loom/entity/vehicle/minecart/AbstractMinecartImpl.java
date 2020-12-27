package org.loomdev.loom.entity.vehicle.minecart;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.entity.vehicle.minecart.Minecart;
import org.loomdev.loom.block.BlockStateImpl;
import org.loomdev.loom.entity.EntityImpl;

import java.util.Optional;

public abstract class AbstractMinecartImpl extends EntityImpl implements Minecart {

    public AbstractMinecartImpl(AbstractMinecart entity) {
        super(entity);
    }

    @NotNull
    @Override
    public AbstractMinecart getMinecraftEntity() {
        return (AbstractMinecart) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public Optional<BlockState> getDisplayedBlock() {
        return Optional.ofNullable(getMinecraftEntity().getDisplayBlockState())
                .map(BlockStateImpl::new);
    }

    @Override
    public void setDisplayedBlock(@Nullable BlockState blockState) {
        getMinecraftEntity().setDisplayBlockState(blockState == null ? null : ((BlockStateImpl) blockState).getMinecraftState());
    }

    @Override
    public int getDisplayedBlockOffset() {
        return getMinecraftEntity().getDisplayOffset();
    }

    @Override
    public void setDisplayedBlockOffset(int offset) {
        getMinecraftEntity().setDisplayOffset(offset);
    }

}
