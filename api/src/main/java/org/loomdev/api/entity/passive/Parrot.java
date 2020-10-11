package org.loomdev.api.entity.passive;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a Parrot entity.
 */
public interface Parrot extends TameableEntity {

    /**
     * Get the {@link Variant} of the Parrot.
     *
     * @return The {@link Variant} of the Parrot.
     */
    @NotNull Variant getVariant();

    /**
     * Set the {@link Variant} of the Parrot.
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
