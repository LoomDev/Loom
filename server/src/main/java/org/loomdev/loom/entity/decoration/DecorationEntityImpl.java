package org.loomdev.loom.entity.decoration;

import net.minecraft.entity.decoration.AbstractDecorationEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.decoration.DecorationEntity;
import org.loomdev.api.util.Direction;
import org.loomdev.loom.entity.EntityImpl;

public abstract class DecorationEntityImpl extends EntityImpl implements DecorationEntity {

    public DecorationEntityImpl(AbstractDecorationEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull AbstractDecorationEntity getMinecraftEntity() {
        return (AbstractDecorationEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull Direction getAttachedDirection() {
        return getFacingDirection().getOpposite();
    }

    @Override
    public @NotNull Direction getFacingDirection() {
        return Direction.getById(getMinecraftEntity().facing.getId());
    }

    @Override
    public void setFacingDirection(@NotNull Direction direction) {
        getMinecraftEntity().setFacing(net.minecraft.util.math.Direction.byId(direction.getId()));
    }
}
