package org.loomdev.api.entity.animal.horse;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.JumpingMount;
import org.loomdev.api.entity.Saddleable;
import org.loomdev.api.entity.animal.Animal;

import java.util.Optional;
import java.util.UUID;

public interface AbstractHorse extends Animal, JumpingMount, Saddleable {

    boolean isJumping();

    void setJumping(boolean jumping);

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
