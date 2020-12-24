package org.loomdev.api.entity.animal;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a parrot entity.
 */
public interface Parrot extends TameableEntity {

    /**
     * Gets the {@link Variant} of the parrot.
     *
     * @return The {@link Variant} of the parrot.
     */
    @NotNull Variant getVariant();

    /**
     * Sets the {@link Variant} of the parrot.
     *
     * @param variant {@link Variant} The new variant of the parrot.
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
