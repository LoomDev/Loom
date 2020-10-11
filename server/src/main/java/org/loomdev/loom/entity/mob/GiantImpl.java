package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.GiantEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Giant;

public class GiantImpl extends HostileEntityImpl implements Giant {

    public GiantImpl(GiantEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.GIANT;
    }

    @Override
    public @NotNull GiantEntity getMinecraftEntity() {
        return (GiantEntity) super.getMinecraftEntity();
    }
}
