package org.loomdev.api.entity.animal.fish;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.DyeColor;

/**
 * Represents a TropicalFish entity.
 */
public interface TropicalFish extends SchoolingFish {

    /**
     * Gets the pattern of the TropicalFish.
     *
     * @return The pattern of the TropicalFish.
     */
    @NotNull Pattern getPattern();

    /**
     * Sets the pattern of the TropicalFish.
     *
     * @param pattern the new pattern of the TropicalFish.
     */
    void setPattern(@NotNull Pattern pattern);

    /**
     * Gets the base color of the TropicalFish.
     *
     * @return The base color of the TropicalFish.
     */
    @NotNull DyeColor getBaseColor();

    /**
     * Sets the base color of the TropicalFish.
     *
     * @param color The new base color of the TropicalFish.
     */
    void setBaseColor(@NotNull DyeColor color);

    /**
     * Gets the pattern color of the TropicalFish.
     *
     * @return The pattern color of the TropicalFish.
     */
    @NotNull DyeColor getPatternColor();

    /**
     * Sets the pattern color of the TropicalFish.
     *
     * @param color The new pattern color of the TropicalFish.
     */
    void setPatternColor(@NotNull DyeColor color);

    /**
     * Different possible {@link TropicalFish} patterns.
     */
    enum Pattern {
        KOB,
        SUNSTREAK,
        SNOOPER,
        DASHER,
        BRINELY,
        SPOTTY,
        FLOPPER,
        STRIPEY,
        GLITTER,
        BLOCKFISH,
        BETTY,
        CLAYFISH
    }
}
