package org.loomdev.api.entity.passive;

import org.jetbrains.annotations.NotNull;

public interface Llama extends AbstractDonkey {

    int getStrength();

    void setStrength(int strength);

    /**
     * Get the {@link Variant} of the Llama.
     *
     * @return The {@link Variant} of the Llama.
     */
    @NotNull Variant getVariant();

    /**
     * Set the {@link Variant} of the Llama.
     *
     * @param variant The new {@link Variant} of the Llama.
     */
    void setVariant(@NotNull Variant variant);

    // TODO inventory

    enum Variant {
        CREAMY,
        WHITE,
        BROWN,
        GRAY
    }
}
