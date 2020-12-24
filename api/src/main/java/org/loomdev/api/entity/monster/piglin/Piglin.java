package org.loomdev.api.entity.monster.piglin;

import org.loomdev.api.entity.Ageable;

/**
 * Represents a piglin entity.
 */
public interface Piglin extends AbstractPiglin, Ageable {

    /**
     * Gets whether the piglin is dancing.
     *
     * @return {@code true} if the piglin is dancing, otherwise {@code false}.
     */
    boolean isDancing();

    /**
     * Sets whether the piglin is dancing.
     *
     * @param flag {@code true} to make the piglin dance, otherwise {@code false}.
     */
    void setDancing(boolean flag);

    /**
     * Gets whether the piglin is charging.
     *
     * @return {@code true} if the piglin is charging, otherwise {@code false}.
     */
    boolean isChargingCrossbow();

    /**
     * Sets whether the piglin is charging.
     *
     * @param flag {@code true} to make the piglin charge, otherwise {@code false}.
     */
    void setChargingCrossbow(boolean flag);

}
