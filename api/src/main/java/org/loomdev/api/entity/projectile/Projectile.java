package org.loomdev.api.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;

import java.util.Optional;

public interface Projectile extends Entity {

    @Nullable
    Entity getOwner();

    void setOwner(@Nullable Entity entity);

    void shoot(double d0, double d1, double d2, float f, float f1);
}
