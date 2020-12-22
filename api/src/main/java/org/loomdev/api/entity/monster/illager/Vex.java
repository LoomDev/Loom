package org.loomdev.api.entity.monster.illager;

import org.loomdev.api.entity.monster.Monster;

/**
 * Represents a vex entity.
 */
public interface Vex extends Monster {

    /**
     * Gets the charging state of the vex.
     *
     * @return The charging state.
     */
    boolean isCharging();

    /**
     * Sets the charging state of the vex.
     * @param charging The new charging state.
     */
    void setCharging(boolean charging);
}
