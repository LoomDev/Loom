package org.loomdev.api.entity.projectile;

import org.loomdev.api.entity.effect.StatusEffect;

/**
 * Represents a spectral arrow entity.
 */
public interface SpectralArrow extends AbstractArrow {

    /**
     * Gets the amount of ticks the glowing {@link StatusEffect} will be applied for.
     *
     * @return The amount of ticks the glowing effect will be applied for.
     */
    int getGlowingDuration();

    /**
     * Sets the amount of ticks the glowing {@link StatusEffect} will be applied for.
     * @param ticks The amount of ticks the glowing {@link StatusEffect} will be applied for.
     */
    void setGlowingDuration(int ticks);
}
