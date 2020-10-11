package org.loomdev.api.entity.projectile;

import org.loomdev.api.entity.Entity;

import javax.annotation.Nullable;

/**
 * Represents a ShulkerBullet entity.
 */
public interface ShulkerBullet extends Projectile {

    /**
     * Get the target {@link Entity} of the bullet.
     *
     * @return The target {@link Entity}.
     */
    @Nullable Entity getTarget();

    /**
     * Set the target {@link Entity} of the bullet.
     *
     * @param target The new target {@link Entity} of the bullet.
     */
    void setTarget(@Nullable Entity target);

}
