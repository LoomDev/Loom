package org.loomdev.api.entity.npc;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.world.Location;

import java.util.Optional;

/**
 * Represent a wandering trader entity.
 */
public interface WanderingTrader extends AbstractVillager {

    @NotNull
    Optional<Location> getWanderingTarget();

    void setWanderingTarget(@Nullable Location location);

    int getDespawnDelay();

    void setDespawnDelay(int ticks);

}
