package org.loomdev.api.entity;

/**
 * Represents a entity with a baby variant.
 */
public interface Ageable {

    /**
     * Gets whether the entity is a baby.
     *
     * @return True if the entity is a baby, otherwise False.
     */
    boolean isBaby();

    /**
     * Sets whether the entity is a baby.
     *
     * @param baby True to make the entity a baby, otherwise False.
     */
    void setBaby(boolean baby);

}
