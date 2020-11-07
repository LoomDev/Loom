package org.loomdev.loom.entity.decoration;

import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.decoration.LeashKnot;

public class LeashKnotImpl extends AbstractHangingEntityImpl implements LeashKnot {

    public LeashKnotImpl(LeashFenceKnotEntity entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<LeashKnot> getType() {
        return EntityType.LEASH_KNOT;
    }

    @Override
    @NotNull
    public LeashFenceKnotEntity getMinecraftEntity() {
        return (LeashFenceKnotEntity) super.getMinecraftEntity();
    }
}
