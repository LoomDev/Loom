package org.loomdev.api.event.world;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.event.Event;
import org.loomdev.api.world.Explosion;

public class ExplosionEvent extends Event {

    private final Explosion explosion;

    public ExplosionEvent(Explosion explosion) {
        this.explosion = explosion;
    }

    @NotNull
    public Explosion getExplosion() {
        return explosion;
    }

    public static class Pre extends ExplosionEvent {

        public Pre(Explosion explosion) {
            super(explosion);
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    public static class Detonate extends ExplosionEvent {

        public Detonate(Explosion explosion) {
            super(explosion);
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    public static class Post extends ExplosionEvent {

        public Post(Explosion explosion) {
            super(explosion);
        }
    }
}
