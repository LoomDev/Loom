package org.loomdev.api.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Shearable;
import org.loomdev.api.entity.effect.StatusEffect;

import java.util.Optional;

public interface Mooshroom extends Cow, Shearable {

    @NotNull
    Optional<StatusEffect> getStewEffect();

    void setStewEffect(@Nullable StatusEffect effect);

    /**
     * Gets the {@link Variant} of the Mooshroom.
     *
     * @return The {@link Variant} of the Mooshroom.
     */
    @NotNull
    Variant getVariant();

    /**
     * Sets the {@link Variant} of the Mooshroom.
     *
     * @param variant The new {@link Variant} of the Mooshroom.
     */
    void setVariant(@NotNull Variant variant);

    enum Variant {

        RED,
        BROWN
    }
}
