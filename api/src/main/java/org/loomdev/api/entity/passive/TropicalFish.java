package org.loomdev.api.entity.passive;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.DyeColor;

/**
 * Represents a TropicalFish entity.
 */
public interface TropicalFish extends SchoolingFish {

    /**
     * Get the pattern of the TropicalFish.
     *
     * @return The pattern of the TropicalFish.
     */
    @NotNull Pattern getPattern();

    /**
     * Set the pattern of the TropicalFish.
     *
     * @param pattern the new pattern of the TropicalFish.
     */
    void setPattern(@NotNull Pattern pattern);

    /**
     * Get the base color of the TropicalFish.
     *
     * @return The base color of the TropicalFish.
     */
    @NotNull DyeColor getBaseColor();

    /**
     * Set the base color of the TropicalFish.
     *
     * @param color The new base color of the TropicalFish.
     */
    void setBaseColor(@NotNull DyeColor color);

    /**
     * Get the pattern color of the TropicalFish.
     *
     * @return The pattern color of the TropicalFish.
     */
    @NotNull DyeColor getPatternColor();

    /**
     * Set the pattern color of the TropicalFish.
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
