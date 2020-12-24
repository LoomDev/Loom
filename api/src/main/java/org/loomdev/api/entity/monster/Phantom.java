package org.loomdev.api.entity.monster;

/**
 * Represents a phantom entity.
 */
public interface Phantom extends MobEntity, Flying {

    /**
     * Gets the size of the phantom
     * <p>Minimum: {@code 1}</p>
     * <p>Maximum: {@code 64}</p>
     *
     * @return The size of the phantom.
     */
    int getSize();

    /**
     * Sets the size of the phantom.
     * <p>Minimum: {@code 1}</p>
     * <p>Maximum: {@code 64}</p>
     *
     * @param size The new size of the phantom
     */
    void setSize(int size);
}
