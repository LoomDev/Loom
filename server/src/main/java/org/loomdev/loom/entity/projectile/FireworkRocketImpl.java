package org.loomdev.loom.entity.projectile;

import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.FireworkRocket;

public class FireworkRocketImpl extends ProjectileImpl implements FireworkRocket {

    public FireworkRocketImpl(FireworkRocketEntity entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<FireworkRocket> getType() {
        return EntityType.FIREWORK_ROCKET;
    }

    @Override
    @NotNull
    public FireworkRocketEntity getMinecraftEntity() {
        return (FireworkRocketEntity) super.getMinecraftEntity();
    }

    @Override
    public void detonate() {
        getMinecraftEntity().explode();
    }

    @Override
    public boolean isShotAtAngle() {
        return getMinecraftEntity().isShotAtAngle();
    }

    @Override
    public void setShortAtAngle(boolean flag) {
        getMinecraftEntity().entityData.set(FireworkRocketEntity.DATA_SHOT_AT_ANGLE, flag);
    }
}
