package org.loomdev.api.entity.passive;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public interface Fox extends AnimalEntity {

    /**
     * Get the {@link Variant} of the Fox.
     *
     * @return The {@link Variant} of the Fox.
     */
    @NotNull Variant getVariant();

    /**
     * Set the {@link Variant} of the Fox.
     *
     * @param variant The new {@link Variant} of the Fox.
     */
    void setVariant(@NotNull Variant variant);

    boolean isCrouching();

    void setCrouching(boolean flag);

    void setSleeping(boolean flag);

    boolean isChasing();

    void setChasing(boolean flag);

    boolean isRollingHead();

    void setRollingHead(boolean flag);

    List<UUID> getTrusted();

    enum Variant {
        RED,
        SNOW
    }
}
