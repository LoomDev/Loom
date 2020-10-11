package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.PiglinEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Piglin;

public class PiglinImpl extends AbstractPiglinImpl implements Piglin {

    public PiglinImpl(PiglinEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PIGLIN;
    }

    @Override
    public @NotNull PiglinEntity getMinecraftEntity() {
        return (PiglinEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isDancing() {
        return getMinecraftEntity().dataTracker.get(PiglinEntity.DANCING);
    }

    @Override
    public void setDancing(boolean flag) {
        getMinecraftEntity().setDancing(flag);
    }

    @Override
    public boolean isCharging() {
        return getMinecraftEntity().dataTracker.get(PiglinEntity.CHARGING);
    }

    @Override
    public void setCharging(boolean flag) {
        getMinecraftEntity().setCharging(flag);
    }

    @Override
    public boolean isBaby() {
        return getMinecraftEntity().dataTracker.get(PiglinEntity.BABY);
    }

    @Override
    public void setBaby(boolean flag) {
        getMinecraftEntity().setBaby(flag);
    }
}
