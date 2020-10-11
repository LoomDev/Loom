package org.loomdev.api.entity.projectile.thrown;

import org.loomdev.api.entity.projectile.Projectile;
import org.loomdev.api.item.ItemStack;

/**
 * Represents a ThrownItem entity.
 */
public interface ThrownItem extends Projectile {

    /**
     * Gets the item of the ThrownItem entity.
     *
     * @return The item.
     */
    ItemStack getItem();

    /**
     * Set the item of the ThrownItem entity.
     *
     * @param item The item.
     */
    void setItem(ItemStack item);

}
