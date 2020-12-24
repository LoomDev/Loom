package org.loomdev.api.entity.npc;

import org.jetbrains.annotations.Nullable;
import org.loomdev.api.world.Location;

/**
 * Represent a wandering trader entity.
 */
public interface WanderingTrader extends AbstractVillager {

    @Nullable
    Location getWanderingTarget();

    void setWanderingTarget(@Nullable Location location);

    int getDespawnDelay();

    void setDespawnDelay(int ticks);

}
