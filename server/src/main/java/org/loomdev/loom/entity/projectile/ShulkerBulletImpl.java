package org.loomdev.loom.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.ShulkerBullet;
import org.loomdev.loom.entity.EntityImpl;

public class ShulkerBulletImpl extends ProjectileImpl implements ShulkerBullet {

    public ShulkerBulletImpl(net.minecraft.world.entity.projectile.ShulkerBullet entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<ShulkerBullet> getType() {
        return EntityType.SHULKER_BULLET;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.ShulkerBullet getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.ShulkerBullet) super.getMinecraftEntity();
    }

    @Nullable
    @Override
    public Entity getTarget() {
        return getMinecraftEntity().finalTarget.getLoomEntity();
    }

    @Override
    public void setTarget(@Nullable Entity entity) {
        if (entity != null) {
            getMinecraftEntity().finalTarget = ((EntityImpl) entity).getMinecraftEntity();
            getMinecraftEntity().targetId = entity.getUniqueId();
        } else {
            getMinecraftEntity().finalTarget = null;
            getMinecraftEntity().targetId = null;
        }
    }
}
