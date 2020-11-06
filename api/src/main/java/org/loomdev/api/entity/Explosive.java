package org.loomdev.api.entity;

/**
 * Represents an explosive entity.
 */
public interface Explosive {

    /**
     * Get the power of the explosion.
     *
     * @return The power of the explosion.
     */
    float getExplosionPower();

    /**
     * Set the power of the explosion.
     *
     * @param power The new power of the explosion.
     */
    void setExplosionPower(int power);
}
