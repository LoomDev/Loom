package org.loomdev.loom.event.server;

import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.event.server.TickEvent;
import org.loomdev.loom.event.EventImpl;

public class TickEventImpl extends EventImpl implements TickEvent {

    private final long tick;

    public TickEventImpl(long tick) {
        this.tick = tick;
    }

    @Override
    public long getTick() {
        return tick;
    }

    public static class PreImpl extends TickEventImpl implements TickEvent.Pre {

        public PreImpl(long tick) {
            super(tick);
        }
    }

    public static class PostImpl extends TickEventImpl implements TickEvent.Post {

        public PostImpl(long tick) {
            super(tick);
        }
    }

    public static class WorldImpl extends TickEventImpl implements TickEvent.World {

        private final org.loomdev.api.world.World world;

        public WorldImpl(long tick, Level level) {
            super(tick);
            this.world = level.getLoomWorld();
        }

        @Override
        @NotNull
        public org.loomdev.api.world.World getWorld() {
            return world;
        }
    }
}
