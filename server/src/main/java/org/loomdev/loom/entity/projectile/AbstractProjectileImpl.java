package org.loomdev.loom.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.projectile.Projectile;
import org.loomdev.loom.entity.EntityImpl;

import java.util.Optional;

public abstract class AbstractProjectileImpl extends EntityImpl implements Projectile {

    public AbstractProjectileImpl(net.minecraft.world.entity.projectile.Projectile entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.Projectile getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.Projectile) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public Optional<Entity> getOwner() {
        return Optional.ofNullable(getMinecraftEntity().getOwner())
                .map(net.minecraft.world.entity.Entity::getLoomEntity);
    }

    @Override
    public void setOwner(@Nullable Entity entity) {
        if (entity == null) {
            getMinecraftEntity().setOwner(null);
            return;
        }

        getMinecraftEntity().setOwner(((EntityImpl) entity).getMinecraftEntity());
    }

    @Override
    public void shoot(double v, double v1, double v2, float v3, float v4) {
        getMinecraftEntity().shoot(v, v1, v2, v3, v4);
    }
}
