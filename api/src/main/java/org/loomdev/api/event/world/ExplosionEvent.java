package org.loomdev.api.event.world;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.event.Cancelable;
import org.loomdev.api.event.Event;
import org.loomdev.api.world.Explosion;

public interface ExplosionEvent extends Event {

    @NotNull
    Explosion getExplosion();

    interface Pre extends ExplosionEvent, Cancelable {
    }

    interface Detonate extends ExplosionEvent, Cancelable {
    }

    interface Post extends ExplosionEvent {
    }
}
