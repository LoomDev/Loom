package org.loomdev.api.entity;

import org.jetbrains.annotations.Nullable;

/**
 * Represents a TNT entity.
 */
public interface Tnt extends Entity, Explosive {

    /**
     * Get the time in ticks until the tnt explodes.
     *
     * @return The time in ticks until the tnt explodes.
     */
    int getFuseTicks();

    /**
     * Set the time in ticks until the tnt explodes.
     *
     * @param ticks The time in ticks until the tnt explodes.
     */
    void setFuseTicks(int ticks);

    /**
     * Get the {@link LivingEntity} that activated the tnt.
     *
     * @return The {@link LivingEntity} that activated the tnt.
     */
    @Nullable LivingEntity getSource();

}
