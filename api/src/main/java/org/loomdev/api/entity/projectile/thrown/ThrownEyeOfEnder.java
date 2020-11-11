package org.loomdev.api.entity.projectile.thrown;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.math.vector.Vector3d;

/**
 * Represents a EyeOfEnder entity.
 */
public interface ThrownEyeOfEnder extends Entity {

    /**
     * Get whether the eye of ender is dropped.
     *
     * @return True if the EyeOfEnder item is dropped, otherwise False.
     */
    boolean getDropsItem();

    /**
     * Set whether the eye of ender is dropped.
     *
     * @param flag True if the EyeOfEnder item should be dropped, otherwise False.
     */
    void setDropsItem(boolean flag);

    /**
     * Get the target of the eye of ender.
     *
     * @return The target of the eye of ender.
     */
    @NotNull Vector3d getTarget();

    /**
     * Set the target location for the eye of ender to fly towards.
     *
     * @param target The new target location for the eye of ender.
     */
    void setTarget(@NotNull Vector3d target);

    /**
     * Get the item that is displayed for the entity.
     *
     * @return The item that is displayed, null if an eye of ender is displayed.
     */
    @NotNull ItemStack getItem();

    /**
     * Set the item that is displayed for the entity.
     *
     * @param item The item to display as the entity, null for an eye of ender.
     */
    void setItem(@NotNull ItemStack item);

}
