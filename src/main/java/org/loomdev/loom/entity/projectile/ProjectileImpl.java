package org.loomdev.loom.entity.projectile;

import net.minecraft.entity.projectile.ProjectileEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.projectile.Projectile;
import org.loomdev.loom.entity.EntityImpl;

import java.util.Optional;

public abstract class ProjectileImpl extends EntityImpl implements Projectile {
    public ProjectileImpl(ProjectileEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull ProjectileEntity getMinecraftEntity() {
        return (ProjectileEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull Optional<Entity> getOwner() {
        return Optional.ofNullable(getMinecraftEntity().getOwner())
                .map(net.minecraft.entity.Entity::getLoomEntity);
    }

    @Override
    public void setOwner(@NotNull Entity entity) {
        getMinecraftEntity().setOwner(((EntityImpl) entity).getMinecraftEntity());
    }

    @Override
    public void setVelocity(double v, double v1, double v2, float v3, float v4) {
        getMinecraftEntity().setVelocity(v, v1, v2, v3, v4);
    }
}
