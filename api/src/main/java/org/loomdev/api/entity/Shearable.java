package org.loomdev.api.entity;

/**
 * Represents a shearable entity.
 */
public interface Shearable {

    /**
     * Gets whether the entity is shearable.
     * @return True if the entity is shearable.
     */
    boolean isShearable();

    /**
     * Shear the entity.
     */
    void shear();

}
