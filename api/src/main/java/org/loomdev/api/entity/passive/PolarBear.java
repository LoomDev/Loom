package org.loomdev.api.entity.passive;

import org.loomdev.api.entity.Ageable;
import org.loomdev.api.entity.mob.Angerable;

/**
 * Represents a PolarBear entity.
 */
public interface PolarBear extends AnimalEntity, Angerable, Ageable {

    /**
     * Get whether the PolarBear is in its warning state.
     *
     * @return True if the PolarBear is in its warning state, otherwise False.
     */
    boolean isWarning();

    /**
     * Set whether the PolarBear is in its warning state.
     *
     * @param flag True to active the warning state, otherwise False.
     */
    void setWarning(boolean flag);

}
