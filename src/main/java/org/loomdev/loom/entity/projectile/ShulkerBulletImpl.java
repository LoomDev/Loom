package org.loomdev.loom.entity.projectile;

import net.minecraft.entity.projectile.ShulkerBulletEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.ShulkerBullet;
import org.loomdev.loom.entity.EntityImpl;

public class ShulkerBulletImpl extends ProjectileImpl implements ShulkerBullet {

    public ShulkerBulletImpl(ShulkerBulletEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SHULKER_BULLET;
    }

    @Override
    public @NotNull ShulkerBulletEntity getMinecraftEntity() {
        return (ShulkerBulletEntity) super.getMinecraftEntity();
    }

    @Nullable
    @Override
    public Entity getTarget() {
        return getMinecraftEntity().target.getLoomEntity();
    }

    @Override
    public void setTarget(@Nullable Entity entity) {
        if (entity != null) {
            getMinecraftEntity().target = ((EntityImpl) entity).getMinecraftEntity();
            getMinecraftEntity().targetUuid = entity.getUniqueId();
        } else {
            getMinecraftEntity().target = null;
            getMinecraftEntity().targetUuid = null;
        }
    }
}
