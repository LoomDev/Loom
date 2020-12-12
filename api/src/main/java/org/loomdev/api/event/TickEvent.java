package org.loomdev.api.event;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.world.World;

public class TickEvent extends Event {

    private final long tick;

    public TickEvent(long tick) {
        this.tick = tick;
    }

    public long getTick() {
        return tick;
    }

    public static class Pre extends TickEvent {

        public Pre(long tick) {
            super(tick);
        }
    }

    public static class Post extends TickEvent {

        public Post(long tick) {
            super(tick);
        }
    }

    public static class Level extends TickEvent {

        private final World world;

        public Level(World world, long tick) {
            super(tick);
            this.world = world;
        }

        @NotNull
        public World getLevel() {
            return world;
        }
    }
}
