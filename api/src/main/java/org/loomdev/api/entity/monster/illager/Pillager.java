package org.loomdev.api.entity.monster.illager;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.monster.illager.Illager;

/**
 * Represents a pillager entity.
 */
public interface Pillager extends Illager {

    /**
     * Gets whether the pillager is charging their crossbow.
     *
     * @return {@code true} if the Pillager is charging, otherwise {@code false}.
     */
    boolean isChargingCrossbow();

    /**
     * Sets whether the pillager is charging their crossbow.
     *
     * @param charging {@code true} to make the Pillager charge, otherwise {@code false}.
     */
    void setChargingCrossbow(boolean charging);

    boolean isAlliedTo(@NotNull Entity entity);
}
