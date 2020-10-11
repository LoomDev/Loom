package org.loomdev.api.entity.passive;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.DyeColor;

public interface Cat extends TameableEntity, Sittable {

    /**
     * Get the {@link Variant} of the Cat.
     *
     * @return The {@link Variant} of the Cat.
     */
    @NotNull Variant getVariant();

    /**
     * Set the {@link Variant} of the Cat.
     *
     * @param variant The new {@link Variant} of the Cat.
     */
    void setVariant(@NotNull Variant variant);

    @NotNull DyeColor getCollarColor();

    void setCollarColor(@NotNull DyeColor dyeColor);

    void hiss();

    enum Variant {
        TABBY,
        BLACK,
        RED,
        SIAMESE,
        BRITISH_SHORTHAIR,
        CALICO,
        PERSIAN,
        RAGDOLL,
        WHITE,
        JELLIE,
        ALL_BLACK;
    }
}
