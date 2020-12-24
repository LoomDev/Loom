package org.loomdev.api.entity.projectile.thrown;

import org.loomdev.api.entity.projectile.Projectile;
import org.loomdev.api.item.ItemStack;

/**
 * Represents a throwable item entity.
 */
public interface ThrowableItem extends Projectile {

    /**
     * Gets the item of the throwable item entity.
     *
     * @return The item.
     */
    ItemStack getItem();

    /**
     * Sets the item of the throwable item entity.
     *
     * @param item The item.
     */
    void setItem(ItemStack item);

}
