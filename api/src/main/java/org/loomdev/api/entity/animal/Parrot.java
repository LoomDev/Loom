package org.loomdev.api.entity.animal;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a parrot entity.
 */
public interface Parrot extends TameableEntity {

    /**
     * Gets the variant of the parrot.
     *
     * @return The variant of the parrot.
     */
    @NotNull Variant getVariant();

    /**
     * Sets the variant of the parrot.
     *
     * @param variant The new variant of the parrot.
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
