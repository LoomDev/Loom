package org.loomdev.api.entity.projectile;

/**
 * Represents a wither skull entity.
 */
public interface WitherSkull extends ExplosiveProjectile {

    /**
     * Gets whether the wither skull is charged.
     *
     * @return {@code true} if the wither skull is charged, otherwise {@code false}.
     */
    boolean isCharged(); // TODO ???? what is charged??

    /**
     * Sets whether the wither skull is charged.
     *
     * @param flag {@code true} if the wither skull should be charged, otherwise {@code false}.
     */
    void setCharged(boolean flag);

}
