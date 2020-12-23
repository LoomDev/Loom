package org.loomdev.api.entity;

import org.loomdev.api.util.DyeColor;

/**
 * Represents a colorable entity.
 */
public interface Colorable {

    /**
     * Gets the {@link DyeColor} of the entity.
     *
     * @return The {@link DyeColor} of the entity.
     */
    DyeColor getColor();

    /**
     * Sets the {@link DyeColor} of the entity.
     *
     * @param color The new {@link DyeColor} of the entity.
     */
    void setColor(DyeColor color);

}
