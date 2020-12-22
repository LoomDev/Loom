package org.loomdev.api.entity.monster.illager;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.monster.illager.Illager;

/**
 * Represents a Pillages entity.
 */
public interface Pillager extends Illager {

    /**
     * Gets whether the Pillager is charging.
     *
     * @return True if the Pillager is charging, otherwise False.
     */
    boolean isChargingCrossbow();

    /**
     * Sets whether the Pillager is charging.
     *
     * @param charging True to make the Pillager charge, otherwise False.
     */
    void setChargingCrossbow(boolean charging);

    boolean isAlliedTo(@NotNull Entity entity);
}
