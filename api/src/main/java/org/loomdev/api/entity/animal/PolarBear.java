package org.loomdev.api.entity.animal;

import org.loomdev.api.entity.Ageable;
import org.loomdev.api.entity.monster.Angerable;

/**
 * Represents a PolarBear entity.
 */
public interface PolarBear extends Animal, Angerable, Ageable {

    /**
     * Gets whether the PolarBear is in its warning state.
     *
     * @return True if the PolarBear is in its warning state, otherwise False.
     */
    boolean isStanding();

    /**
     * Sets whether the PolarBear is in its warning state.
     *
     * @param standing True to active the warning state, otherwise False.
     */
    void setStanding(boolean standing);
}
