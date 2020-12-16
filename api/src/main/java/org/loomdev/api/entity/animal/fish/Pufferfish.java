package org.loomdev.api.entity.animal.fish;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.animal.Bucketable;

/**
 * Represents a Pufferfish entity.
 */
public interface Pufferfish extends Fish, Bucketable {

    /**
     * Get the current {@link PuffState} of the Pufferish.
     *
     * @return The current {@link PuffState}.
     */
    @NotNull
    PuffState getPuffState();

    /**
     * Set the {@link PuffState} of the Pufferish.
     *
     * @param puffState The new {@link PuffState}
     */
    void setPuffState(@NotNull PuffState puffState);

    int getDeflateTicks();

    void setDeflateTicks(int ticks);

    /**
     * Represents the puffed up state of a {@link Pufferfish}.
     */
    enum PuffState {

        DEFLATED,
        HALF_PUFFED_UP,
        PUFFED_UP
    }
}
