package org.loomdev.api.entity.projectile;

/**
 * Represents a SpectralArrow entity.
 */
public interface SpectralArrow extends PersistentProjectile {

    /**
     * Get the amount of ticks the glowing {@link org.loomdev.api.entity.effect.StatusEffect} will be applied for.
     *
     * @return The amount of ticks the glowing {@link org.loomdev.api.entity.effect.StatusEffect} will be applied for.
     */
    int getGlowingDuration();

    /**
     * Set the amount of ticks the glowing {@link org.loomdev.api.entity.effect.StatusEffect} will be applied for.
     * @param ticks The amount of ticks the glowing {@link org.loomdev.api.entity.effect.StatusEffect} will be applied for.
     */
    void setGlowingDuration(int ticks);

}
