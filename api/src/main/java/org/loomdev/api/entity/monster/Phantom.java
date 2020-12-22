package org.loomdev.api.entity.monster;

/**
 * Represents a Phantom entity.
 */
public interface Phantom extends MobEntity, Flying {

    /**
     * Gets the size of the phantom
     * <p>minimum 1, maximum 64</p>
     *
     * @return The size of the phantom.
     */
    int getSize();

    /**
     * Sets the size of the phantom.
     * <p>minimum 1, maximum 64</p>
     *
     * @param size The new size of the phantom
     */
    void setSize(int size);
}
