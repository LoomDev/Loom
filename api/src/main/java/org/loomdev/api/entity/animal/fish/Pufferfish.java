package org.loomdev.api.entity.animal.fish;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.animal.Bucketable;

/**
 * Represents a pufferfish entity.
 */
public interface Pufferfish extends Fish, Bucketable {

    /**
     * Gets the current {@link PuffState} of the pufferfish.
     *
     * @return The current {@link PuffState}.
     */
    @NotNull
    PuffState getPuffState();

    /**
     * Sets the {@link PuffState} of the pufferfish.
     *
     * @param puffState The new {@link PuffState}
     */
    void setPuffState(@NotNull PuffState puffState);

    int getDeflateTicks();

    void setDeflateTicks(int ticks);

    /**
     * Represents the puffed up state of a pufferfish.
     */
    enum PuffState {

        DEFLATED,
        HALF_PUFFED_UP,
        PUFFED_UP
    }
}
