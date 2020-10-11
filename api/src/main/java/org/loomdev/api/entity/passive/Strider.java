package org.loomdev.api.entity.passive;

import org.loomdev.api.entity.Saddleable;

/**
 * Represents a Strider entity.
 */
public interface Strider extends AnimalEntity, Saddleable {
    // TODO possible add things todo with the stick steering item

    /**
     * Get whether the Strider is cold.
     *
     * @return True is the Strider is cold, otherwise False.
     */
    boolean isCold();

    /**
     * Set whether the Strider is cold.
     *
     * @param flag True is cold, otherwise False.
     */
    void setCold(boolean flag);

}
