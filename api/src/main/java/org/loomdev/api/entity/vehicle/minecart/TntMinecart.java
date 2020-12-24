package org.loomdev.api.entity.vehicle.minecart;

import org.loomdev.api.entity.Explosive;

/**
 * Represents a minecart with tnt inside.
 */
public interface TntMinecart extends Minecart, Explosive {

    /**
     * Gets the remaining amount of ticks before the minecart explodes.
     *
     * @return The amount of ticks remaining before the minecart explodes.
     */
    int getRemainingFuseTicks();

    /**
     * Sets the remaining amount of ticks before the minecart explodes.
     *
     * @param ticks The new amount of ticks remaining before the minecart explodes.
     */
    void setRemainingFuseTicks(int ticks);

}
