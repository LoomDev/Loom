package org.loomdev.loom.entity.decoration;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.decoration.HangingEntity;
import org.loomdev.api.util.Direction;
import org.loomdev.loom.entity.EntityImpl;

public abstract class AbstractHangingEntityImpl extends EntityImpl implements HangingEntity {

    public AbstractHangingEntityImpl(net.minecraft.world.entity.decoration.HangingEntity entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.decoration.HangingEntity getMinecraftEntity() {
        return (net.minecraft.world.entity.decoration.HangingEntity) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public Direction getAttachedDirection() {
        return getFacingDirection().getOpposite();
    }

    @Override
    @NotNull
    public Direction getFacingDirection() {
        return Direction.getById(getMinecraftEntity().direction.get3DDataValue());
    }

    @Override
    public void setFacingDirection(@NotNull Direction direction) {
        getMinecraftEntity().setDirection(net.minecraft.core.Direction.from3DDataValue(direction.getId()));
    }
}
