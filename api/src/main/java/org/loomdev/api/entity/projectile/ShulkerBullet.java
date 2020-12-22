package org.loomdev.api.entity.projectile;

import org.loomdev.api.entity.Entity;

import javax.annotation.Nullable;

/**
 * Represents a shulker entity.
 */
public interface ShulkerBullet extends Projectile {

    /**
     * Gets the target {@link Entity} of the shulker bullet.
     *
     * @return The target {@link Entity}.
     */
    @Nullable Entity getTarget();

    /**
     * Sets the target {@link Entity} of the bullet.
     *
     * @param target The new target {@link Entity} of the bullet.
     */
    void setTarget(@Nullable Entity target);

}
