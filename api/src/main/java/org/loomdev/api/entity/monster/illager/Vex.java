package org.loomdev.api.entity.monster.illager;

import org.loomdev.api.entity.monster.Monster;

/**
 * Represents a Vex entity.
 */
public interface Vex extends Monster {

    /**
     * Get the charging state of the Vex.
     *
     * @return The charging state.
     */
    boolean isCharging();

    /**
     * Set the charging state of the Vex.
     * @param charging The new charging state.
     */
    void setCharging(boolean charging);
}
