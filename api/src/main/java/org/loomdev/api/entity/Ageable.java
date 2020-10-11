package org.loomdev.api.entity;

/**
 * Represents a entity with a baby variant.
 */
public interface Ageable {

    /**
     * Get whether the entity is a baby.
     *
     * @return True if the entity is a baby, otherwise False.
     */
    boolean isBaby();

    /**
     * Set whether the entity is a baby.
     *
     * @param flag True to make the entity a baby, otherwise False.
     */
    void setBaby(boolean flag);

}
