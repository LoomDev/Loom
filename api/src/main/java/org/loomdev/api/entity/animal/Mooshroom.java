package org.loomdev.api.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Shearable;
import org.loomdev.api.entity.effect.StatusEffect;

public interface Mooshroom extends Cow, Shearable {

    @Nullable
    StatusEffect getStewEffect();

    void setStewEffect(@Nullable StatusEffect effect);

    /**
     * Gets the variant of the Mooshroom.
     *
     * @return The variant of the Mooshroom.
     */
    @NotNull
    Variant getVariant();

    /**
     * Sets the variant of the Mooshroom.
     *
     * @param variant The new variant of the Mooshroom.
     */
    void setVariant(@NotNull Variant variant);

    enum Variant {

        RED,
        BROWN
    }
}
