package org.loomdev.api.entity.animal;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public interface Fox extends Animal {

    /**
     * Gets the {@link Variant} of the fox.
     *
     * @return The {@link Variant} of the fox.
     */
    @NotNull
    Variant getVariant();

    /**
     * Sets the {@link Variant} of the fox.
     *
     * @param variant The new {@link Variant} of the fox.
     */
    void setVariant(@NotNull Variant variant);

    boolean isCrouching();

    void setCrouching(boolean flag);

    void setSleeping(boolean flag);

    boolean isPouncing();

    void setPouncing(boolean pouncing);

    boolean isRollingHead();

    void setRollingHead(boolean flag);

    @NotNull
    List<UUID> getTrusted();

    void trust(@NotNull UUID uuid);

    enum Variant {
        RED,
        SNOW
    }
}
