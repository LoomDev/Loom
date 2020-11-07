package org.loomdev.loom.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.projectile.Projectile;
import org.loomdev.loom.entity.EntityImpl;

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
    @Nullable
    public Entity getOwner() {
        var owner = getMinecraftEntity().getOwner();
        if (owner == null) return null;
        return owner.getLoomEntity();
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
