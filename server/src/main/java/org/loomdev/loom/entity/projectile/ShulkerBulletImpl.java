package org.loomdev.loom.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.projectile.ShulkerBullet;
import org.loomdev.loom.entity.EntityImpl;

import java.util.Optional;

public class ShulkerBulletImpl extends AbstractProjectileImpl implements ShulkerBullet {

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

    @Override
    @NotNull
    public Optional<Entity> getTarget() {
        return Optional.ofNullable(getMinecraftEntity().finalTarget)
                .map(net.minecraft.world.entity.Entity::getLoomEntity);
    }

    @Override
    public void setTarget(@Nullable Entity entity) {
        if (entity != null) {
            getMinecraftEntity().finalTarget = ((EntityImpl) entity).getMinecraftEntity();
            getMinecraftEntity().targetId = entity.getUUID();
        } else {
            getMinecraftEntity().finalTarget = null;
            getMinecraftEntity().targetId = null;
        }
    }
}
