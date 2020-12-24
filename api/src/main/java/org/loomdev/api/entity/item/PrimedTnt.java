package org.loomdev.api.entity.item;

import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.Explosive;
import org.loomdev.api.entity.LivingEntity;

/**
 * Represents a TNT entity.
 */
public interface PrimedTnt extends Entity, Explosive {

    /**
     * Gets the time in ticks until the TNT explodes.
     *
     * @return The time in ticks until the TNT explodes.
     */
    int getFuse();

    /**
     * Sets the time in ticks until the TNT explodes.
     *
     * @param ticks The time in ticks until the TNT explodes.
     */
    void setFuse(int ticks);

    /**
     * Gets the {@link LivingEntity} that activated the TNT.
     *
     * @return The {@link LivingEntity} that activated the TNT.
     */
    @Nullable
    LivingEntity getSource();
}
