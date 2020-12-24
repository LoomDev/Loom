package org.loomdev.api.entity.monster;

import org.loomdev.api.entity.Saddleable;
import org.loomdev.api.entity.animal.Animal;

/**
 * Represents a strider entity.
 */
public interface Strider extends Animal, Saddleable {
    // TODO possible add things todo with the stick steering item

    /**
     * Gets whether the strider is cold.
     *
     * @return {@code true} is the strider is cold, otherwise {@code false}.
     */
    boolean isShivering();

    /**
     * Sets whether the strider is cold.
     *
     * @param shivering {@code true} is cold, otherwise {@code false}.
     */
    void setShivering(boolean shivering);

}
