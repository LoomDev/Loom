package org.loomdev.api.entity.monster.piglin;

import org.loomdev.api.entity.Ageable;

/**
 * Represents a piglin entity.
 */
public interface Piglin extends AbstractPiglin, Ageable {

    /**
     * Gets whether the Piglin is dancing.
     *
     * @return {@code true} if the Piglin is dancing, otherwise {@code false}.
     */
    boolean isDancing();

    /**
     * Sets whether the Piglin dancing state.
     *
     * @param flag {@code true} to make the Piglin dance, otherwise {@code false}.
     */
    void setDancing(boolean flag);

    /**
     * Gets whether the Piglin is charging.
     *
     * @return {@code true} if the Piglin is charging, otherwise {@code false}.
     */
    boolean isChargingCrossbow();

    /**
     * Sets whether the Piglin is charging.
     *
     * @param flag {@code true} to make the Piglin charge, otherwise {@code false}.
     */
    void setChargingCrossbow(boolean flag);

}
