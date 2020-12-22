package org.loomdev.api.entity.animal.horse;

import org.jetbrains.annotations.NotNull;

public interface Llama extends ChestedHorse {

    int getStrength();

    void setStrength(int strength);

    /**
     * Gets the {@link Variant} of the Llama.
     *
     * @return The {@link Variant} of the Llama.
     */
    @NotNull Variant getVariant();

    /**
     * Sets the {@link Variant} of the Llama.
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
