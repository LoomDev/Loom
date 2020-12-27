package org.loomdev.api.entity.misc;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.player.Player;

import java.util.Optional;

/**
 * Represents an experience orb entity.
 */
public interface ExperienceOrb extends Entity {

    @NotNull
    Optional<Player> getTarget();

    void setTarget(Player player);

    int getAmount();

    void setAmount(int amount);

    /**
     * Gets the health of the experience orb.
     * <p>
     *     By default a experience orb has a health of 5.
     *     When the health is equal to or lower than 0, the experience orb will despawn.
     * </p>
     *
     * @return The health of the experience orb.
     */
    int getHealth();

    /**
     * Sets the health of the experience orb.
     * <p>
     *     By default a experience orb has a health of 5.
     *     When the health is equal to or lower than 0, the experience orb will despawn.
     * </p>
     *
     * @param health The new health of the experience orb.
     */
    void setHealth(int health);

    /**
     * Gets the age of the orb.
     * <p>An experience orb despawns after 6000 ticks.</p>
     *
     * @return The age of the experience orb.
     */
    int getOrbAge();

    /**
     * Sets the age of the orb.
     * <p>An experience orb despawns after 6000 ticks.</p>
     *
     * @param ticks The new age of the orb in ticks.
     */
    void setOrbAge(int ticks);
}
