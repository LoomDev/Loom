package org.loomdev.api.entity.monster.piglin;

import org.loomdev.api.entity.Ageable;

/**
 * Represents a Piglin entity.
 */
public interface Piglin extends AbstractPiglin, Ageable {

    /**
     * Get whether the Piglin is dancing.
     *
     * @return True if the Piglin is dancing, otherwise False.
     */
    boolean isDancing();

    /**
     * Set whether the Piglin dancing state.
     *
     * @param flag True to make the Piglin dance, otherwise False.
     */
    void setDancing(boolean flag);

    /**
     * Get whether the Piglin is charging.
     *
     * @return True if the Piglin is charging, otherwise False.
     */
    boolean isChargingCrossbow();

    /**
     * Set whether the Piglin is charging.
     *
     * @param flag True to make the Piglin charge, otherwise False.
     */
    void setChargingCrossbow(boolean flag);

}
