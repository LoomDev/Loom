package org.loomdev.api.entity.passive;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.JumpingMount;
import org.loomdev.api.entity.Saddleable;

import java.util.Optional;
import java.util.UUID;

public interface HorseBase extends AnimalEntity, JumpingMount, Saddleable {
    boolean isInAir();

    void setInAir(boolean flag);

    boolean isTame();

    void setTame(boolean flag);

    boolean isEatingGrass();

    boolean isAngry();

    boolean isBred();

    void setBred(boolean flag);

    int getTemper();

    void setTemper(int temper);

    int addTemper(int temper);

    int getMaxTemper();

    void setMaxTemper(int temper);

    void resetMaxTemper();

    Optional<UUID> getOwner();

    void setOwner(@NotNull UUID uuid);

}
