package org.loomdev.api.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Represents a shulker bullet entity.
 */
public interface ShulkerBullet extends Projectile {

    /**
     * Gets the target {@link Entity} of the bullet.
     *
     * @return The target {@link Entity}.
     */
    @NotNull
    Optional<Entity> getTarget();

    /**
     * Sets the target {@link Entity} of the bullet.
     *
     * @param target The new target {@link Entity} of the bullet.
     */
    void setTarget(@Nullable Entity target);
}
