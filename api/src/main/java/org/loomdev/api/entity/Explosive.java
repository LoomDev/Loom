package org.loomdev.api.entity;

/**
 * Represents an explosive entity.
 */
public interface Explosive {

    /**
     * Gets the power of the explosion.
     *
     * @return The power of the explosion.
     */
    float getExplosionPower();

    /**
     * Sets the power of the explosion.
     *
     * @param power The new power of the explosion.
     */
    void setExplosionPower(int power);
}
