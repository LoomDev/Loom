package org.loomdev.api.entity.projectile;

/**
 * Represents a wither skull entity.
 */
public interface WitherSkull extends ExplosiveProjectile {

    /**
     * Get whether the wither skull is charged.
     *
     * @return True if the wither skull is charger, otherwise false.
     */
    boolean isCharged();

    /**
     * Set whether the wither skull is charged.
     *
     * @param flag True if the wither skull should be charged, otherwise false.
     */
    void setCharged(boolean flag);

}
