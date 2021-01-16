package org.loomdev.api.event.server;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.event.Event;
import org.loomdev.api.world.World;

public interface TickEvent extends Event {

    long getTick();

    interface Pre extends TickEvent {
    }

    interface Post extends TickEvent {
    }

    interface World extends TickEvent {

        @NotNull
        org.loomdev.api.world.World getWorld();
    }
}
