package org.loomdev.api.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Colorable;
import org.loomdev.api.entity.Shearable;
import org.loomdev.api.util.DyeColor;

/**
 * Represents a sheep entity.
 */
public interface Sheep extends Animal, Shearable, Colorable {

    /**
     * Gets the {@link DyeColor} of the sheep.
     *
     * @return The {@link DyeColor} of the sheep.
     */
    @Override
    @NotNull DyeColor getColor();

    /**
     * Sets the {@link DyeColor} of the sheep.
     *
     * @param color The new {@link DyeColor} of the sheep.
     */
    @Override
    void setColor(@NotNull DyeColor color);

    /**
     * Sets whether the sheep is sheared or not.
     *
     * @param sheared True is the sheep is in a sheared state, otherwise false.
     */
    void setSheared(boolean sheared);

}
