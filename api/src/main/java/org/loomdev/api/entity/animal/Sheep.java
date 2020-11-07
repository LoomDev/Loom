package org.loomdev.api.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Colorable;
import org.loomdev.api.entity.Shearable;
import org.loomdev.api.util.DyeColor;

/**
 * Represents a Sheep entity.
 */
public interface Sheep extends Animal, Shearable, Colorable {

    /**
     * Get the {@link DyeColor} of the sheep.
     *
     * @return The {@link DyeColor} of the sheep.
     */
    @Override
    @NotNull DyeColor getColor();

    /**
     * Set the {@link DyeColor} of the sheep.
     *
     * @param color The new {@link DyeColor} of the sheep.
     */
    @Override
    void setColor(@NotNull DyeColor color);

    /**
     * Set whether the Sheep is sheared or not.
     *
     * @param flag True is the Sheep is in a sheared state, otherwise false.
     */
    void setSheared(boolean flag);

}
