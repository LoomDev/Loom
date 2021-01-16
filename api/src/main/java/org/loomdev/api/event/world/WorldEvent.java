package org.loomdev.api.event.world;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.event.Cancelable;
import org.loomdev.api.event.Event;
import org.loomdev.api.world.World;

public interface WorldEvent extends Event {

    @NotNull
    World getWorld();

    interface TimeSkip extends WorldEvent, Cancelable {

        long getSkippedTicks();

        void setSkippedTicks(long skippedTicks);
    }
}
