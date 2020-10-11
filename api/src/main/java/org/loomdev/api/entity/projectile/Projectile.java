package org.loomdev.api.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;

import java.util.Optional;

public interface Projectile extends Entity {

    @NotNull Optional<Entity> getOwner();

    void setOwner(@NotNull Entity entity);

    void setVelocity(double d0, double d1, double d2, float f, float f1);

}
