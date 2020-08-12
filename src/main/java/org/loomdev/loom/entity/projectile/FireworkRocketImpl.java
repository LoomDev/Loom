package org.loomdev.loom.entity.projectile;

import net.minecraft.entity.projectile.FireworkRocketEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.FireworkRocket;

public class FireworkRocketImpl extends ProjectileImpl implements FireworkRocket {

    public FireworkRocketImpl(FireworkRocketEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.FIREWORK_ROCKET;
    }

    @Override
    public FireworkRocketEntity getMinecraftEntity() {
        return (FireworkRocketEntity) super.getMinecraftEntity();
    }

    @Override
    public void detonate() {
        getMinecraftEntity().explodeAndRemove();
    }

    @Override
    public boolean isShotAtAngle() {
        return getMinecraftEntity().wasShotAtAngle();
    }

    @Override
    public void setShortAtAngle(boolean b) {
        getMinecraftEntity().dataTracker.set(FireworkRocketEntity.SHOT_AT_ANGLE, b);
    }
}
