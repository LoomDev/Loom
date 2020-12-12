package org.loomdev.loom.event;

import net.kyori.event.EventSubscriber;
import net.kyori.event.SimpleEventBus;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.event.Event;

public class LoomEventBus extends SimpleEventBus<Event> {

    public LoomEventBus() {
        super(Event.class);
    }

    @Override
    protected boolean shouldPost(@NonNull Event event, @NonNull EventSubscriber<?> subscriber) {
        if (event.isCancelable()) {
            return !event.isCanceled();
        }

        return true;
    }
}
