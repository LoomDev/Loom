package org.loomdev.api.entity;

/**
 * Represents a entity with a baby variant.
 */
public interface Ageable {

    /**
     * Gets whether the entity is a baby.
     *
     * @return {@code true} if the entity is a baby, otherwise {@code false}.
     */
    boolean isBaby();

    /**
     * Sets whether the entity is a baby.
     *
     * @param baby {@code true} to make the entity a baby, otherwise {@code false}.
     */
    void setBaby(boolean baby);

}
