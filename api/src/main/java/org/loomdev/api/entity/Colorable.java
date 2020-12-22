package org.loomdev.api.entity;

import org.loomdev.api.util.DyeColor;

/**
 * Represents a colorable entity.
 */
public interface Colorable {

    /**
     * Gets the dye color of the entity.
     *
     * @return The dye color of the entity.
     */
    DyeColor getColor();

    /**
     * Sets the dye color of the entity.
     *
     * @param color The new dye color of the entity.
     */
    void setColor(DyeColor color);

}
