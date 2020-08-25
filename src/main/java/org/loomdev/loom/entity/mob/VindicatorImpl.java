package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.VindicatorEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Vindicator;

public class VindicatorImpl extends IllagerImpl implements Vindicator {

    public VindicatorImpl(VindicatorEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.VINDICATOR;
    }

    @Override
    public @NotNull VindicatorEntity getMinecraftEntity() {
        return (VindicatorEntity) super.getMinecraftEntity();
    }
}
