package org.loomdev.api.event.world;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.event.Event;
import org.loomdev.api.world.World;

public class WorldEvent extends Event {

    private final World world;

    public WorldEvent(World world) {
        this.world = world;
    }

    @NotNull
    public World getWorld() {
        return world;
    }

    public static class TimeSkip extends WorldEvent {

        private long skippedTicks;

        public TimeSkip(World world, long skippedTicks) {
            super(world);
            this.skippedTicks = skippedTicks;
        }

        public long getSkippedTicks() {
            return skippedTicks;
        }

        public void setSkippedTicks(long skippedTicks) {
            this.skippedTicks = skippedTicks;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }
}
