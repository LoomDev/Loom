package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.PillagerEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Pillager;

public class PillagerImpl extends IllagerImpl implements Pillager {

    public PillagerImpl(PillagerEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PILLAGER;
    }

    @Override
    public PillagerEntity getMinecraftEntity() {
        return (PillagerEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isCharging() {
        return getMinecraftEntity().dataTracker.get(PillagerEntity.CHARGING);
    }

    @Override
    public void setCharging(boolean flag) {
        getMinecraftEntity().setCharging(flag);
    }
}
