package org.loomdev.api.entity.animal.horse;

import org.jetbrains.annotations.NotNull;

public interface Llama extends ChestedHorse {

    int getStrength();

    void setStrength(int strength);

    /**
     * Gets the variant of the llama.
     *
     * @return The variant of the llama.
     */
    @NotNull Variant getVariant();

    /**
     * Sets the variant of the llama.
     *
     * @param variant The new variant of the llama.
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
