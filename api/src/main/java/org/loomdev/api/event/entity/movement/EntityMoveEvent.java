package org.loomdev.api.event.entity.movement;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.Cancellable;
import org.loomdev.api.event.player.PlayerEvent;
import org.loomdev.api.world.Location;

/**
 * Fired when a player moves in of the world from one location to another.
 * The player will snap back to their previous location if this event is cancelled.
 */
public class EntityMoveEvent extends PlayerEvent implements Cancellable {

    private Location currentLocation;
    private Location newLocation;
    private boolean cancelled;

    public EntityMoveEvent(Player player, Location currentLocation, Location newLocation) {
        super(player);
        this.currentLocation = currentLocation;
        this.newLocation = newLocation;
    }

    @NotNull
    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(@NotNull Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    @NotNull
    public Location getNewLocation() {
        return newLocation;
    }

    public void setNewLocation(@NotNull Location newLocation) {
        this.newLocation = newLocation;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
