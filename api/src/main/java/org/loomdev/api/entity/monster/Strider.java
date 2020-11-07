package org.loomdev.api.entity.monster;

import org.loomdev.api.entity.Saddleable;
import org.loomdev.api.entity.animal.Animal;

/**
 * Represents a Strider entity.
 */
public interface Strider extends Animal, Saddleable {
    // TODO possible add things todo with the stick steering item

    /**
     * Get whether the Strider is cold.
     *
     * @return True is the Strider is cold, otherwise False.
     */
    boolean isShivering();

    /**
     * Set whether the Strider is cold.
     *
     * @param shivering True is cold, otherwise False.
     */
    void setShivering(boolean shivering);

}
