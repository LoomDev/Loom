package org.loomdev.api.entity.passive;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a Pufferfish entity.
 */
public interface Pufferfish extends Fish {

    /**
     * Get the current {@link PuffState} of the Pufferish.
     *
     * @return The current {@link PuffState}.
     */
    @NotNull PuffState getPuffState();

    /**
     * Set the {@link PuffState} of the Pufferish.
     *
     * @param puffState The new {@link PuffState}
     */
    void setPuffState(@NotNull PuffState puffState);

    /**
     * Represents the puffed up state of a {@link Pufferfish}.
     */
    enum PuffState {
        DEFLATED,
        HALF_PUFFED_UP,
        PUFFED_UP
    }

}
