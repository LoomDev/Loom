package org.loomdev.api.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.DyeColor;

public interface Cat extends TameableEntity, Sittable {

    /**
     * Gets the variant of the cat.
     *
     * @return The variant of the cat.
     */
    @NotNull Variant getVariant();

    /**
     * Sets the variant of the cat.
     *
     * @param variant The new variant of the cat.
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
