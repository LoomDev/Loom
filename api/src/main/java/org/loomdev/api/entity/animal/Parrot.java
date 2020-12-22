package org.loomdev.api.entity.animal;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a Parrot entity.
 */
public interface Parrot extends TameableEntity {

    /**
     * Gets the {@link Variant} of the Parrot.
     *
     * @return The {@link Variant} of the Parrot.
     */
    @NotNull Variant getVariant();

    /**
     * Sets the {@link Variant} of the Parrot.
     *
     * @param variant The new {@link Variant} of the Parrot.
     */
    void setVariant(@NotNull Variant variant);

    enum Variant {
        RED,
        BLUE,
        GREEN,
        CYAN,
        GRAY
    }
}
