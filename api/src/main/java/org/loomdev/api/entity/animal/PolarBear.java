package org.loomdev.api.entity.animal;

import org.loomdev.api.entity.Ageable;
import org.loomdev.api.entity.monster.Angerable;

/**
 * Represents a polar bear entity.
 */
public interface PolarBear extends Animal, Angerable, Ageable {

    /**
     * Gets whether the polar bear is in its warning state.
     *
     * @return {@code true} if the polar bear is in its warning state, otherwise {@code false}.
     */
    boolean isStanding();

    /**
     * Sets whether the polar bear is in its warning state.
     *
     * @param standing {@code true} to active the warning state, otherwise {@code false}.
     */
    void setStanding(boolean standing);
}
