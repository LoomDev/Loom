package org.loomdev.api.entity.mob;

/**
 * Represents a Pillages entity.
 */
public interface Pillager extends Illager {

    /**
     * Get whether the Pillager is charging.
     *
     * @return True if the Pillager is charging, otherwise False.
     */
    boolean isCharging();

    /**
     * Set whether the Pillager is charging.
     *
     * @param flag True to make the Pillager charge, otherwise False.
     */
    void setCharging(boolean flag);

}
