package org.loomdev.api.entity.passive;

import org.jetbrains.annotations.Nullable;
import org.loomdev.api.world.Location;

/**
 * Represent a Wandering Trader entity.
 */
public interface WanderingTrader extends AbstractTrader {

    @Nullable
    Location getWanderingTarget();

    void setWanderingTarget(@Nullable Location location);

    int getDespawnDelay();

    void setDespawnDelay(int ticks);

}
