package org.loomdev.loom.event.world;

import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.event.Cancelable;
import org.loomdev.api.event.world.WorldEvent;
import org.loomdev.api.world.World;
import org.loomdev.loom.event.EventImpl;

public class WorldEventImpl extends EventImpl implements WorldEvent, Cancelable {

    private final World world;
    private boolean canceled;

    public WorldEventImpl(Level level) {
        this.world = level.getLoomWorld();
    }

    @Override
    @NotNull
    public World getWorld() {
        return world;
    }

    @Override
    public boolean isCanceled() {
        return canceled;
    }

    @Override
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public static class TimeSkipImpl extends WorldEventImpl implements WorldEvent.TimeSkip {

        private long skippedTicks;

        public TimeSkipImpl(Level level, long skippedTicks) {
            super(level);
            this.skippedTicks = skippedTicks;
        }

        @Override
        public long getSkippedTicks() {
            return skippedTicks;
        }

        @Override
        public void setSkippedTicks(long skippedTicks) {
            this.skippedTicks = skippedTicks;
        }
    }
}
