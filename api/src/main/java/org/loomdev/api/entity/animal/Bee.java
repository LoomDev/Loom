package org.loomdev.api.entity.animal;

import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.monster.Angerable;
import org.loomdev.api.world.Location;

import java.util.Optional;

public interface Bee extends Animal, Angerable {

    Optional<Location> getFlowerLocation();

    void setFlowerLocation(@Nullable Location location);

    boolean hasFlower();

    Optional<Location> getHiveLocation();

    void setHiveLocation(@Nullable Location location);

    boolean hasHive();

    boolean hasNectar();

    void setHasNectar(boolean stung);

    boolean hasStung();

    void setHasStung(boolean stung);

    int getTicksUntilCanPollinate();

    void setTicksUntilCanPollinate(int ticks);

    int getCannotEnterHiveTicks();

    void setCannotEnterHiveTicks(int ticks);
}
