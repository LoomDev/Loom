package org.loomdev.api.entity.animal.fish;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.DyeColor;

/**
 * Represents a tropical fish entity.
 */
public interface TropicalFish extends SchoolingFish {

    /**
     * Gets the {@link Pattern} of the tropical fish.
     *
     * @return The {@link Pattern} of the tropical fish.
     */
    @NotNull Pattern getPattern();

    /**
     * Sets the {@link Pattern} of the tropical fish.
     *
     * @param pattern The new {@link Pattern} of the tropical fish.
     */
    void setPattern(@NotNull Pattern pattern);

    /**
     * Gets the base color of the tropical fish.
     *
     * @return The base color of the tropical fish.
     */
    @NotNull DyeColor getBaseColor();

    /**
     * Sets the base color of the tropical fish.
     *
     * @param color The new base color of the tropical fish.
     */
    void setBaseColor(@NotNull DyeColor color);

    /**
     * Gets the pattern color of the tropical fish.
     *
     * @return The pattern color of the tropical fish.
     */
    @NotNull DyeColor getPatternColor();

    /**
     * Sets the pattern color of the tropical fish.
     *
     * @param color The new pattern color of the tropical fish.
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
