package org.loomdev.api.entity.projectile.thrown;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.math.vector.Vector3d;

/**
 * Represents an eye of ender entity.
 */
public interface ThrownEyeOfEnder extends Entity {

    /**
     * Gets whether the eye of ender is dropped.
     *
     * @return {@code true} if the eye of ender item is dropped, otherwise {@code false}.
     */
    boolean getDropsItem();

    /**
     * Sets whether the eye of ender is dropped.
     *
     * @param flag {@code true} if the eye of ender item should be dropped, otherwise {@code false}.
     */
    void setDropsItem(boolean flag);

    /**
     * Gets the target of the eye of ender.
     *
     * @return The target of the eye of ender.
     */
    @NotNull Vector3d getTarget();

    /**
     * Sets the target location for the eye of ender to fly towards.
     *
     * @param target The new target location for the eye of ender.
     */
    void setTarget(@NotNull Vector3d target);

    /**
     * Gets the item that is displayed for the entity.
     *
     * @return The item that is displayed, null if an eye of ender is displayed.
     */
    @NotNull ItemStack getItem();

    /**
     * Sets the item that is displayed for the entity.
     *
     * @param item The item to display as the entity, null for an eye of ender.
     */
    void setItem(@NotNull ItemStack item);

}
