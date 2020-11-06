package org.loomdev.api.entity.mob;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;

/**
 * Represents a Pillages entity.
 */
public interface Pillager extends Illager {

    /**
     * Get whether the Pillager is charging.
     *
     * @return True if the Pillager is charging, otherwise False.
     */
    boolean isChargingCrossbow();

    /**
     * Set whether the Pillager is charging.
     *
     * @param charging True to make the Pillager charge, otherwise False.
     */
    void setChargingCrossbow(boolean charging);

    boolean isAlliedTo(@NotNull Entity entity);
}
