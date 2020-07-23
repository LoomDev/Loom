package org.loomdev.loom.event;

import net.kyori.event.EventSubscriber;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.event.Event;
import org.loomdev.api.event.EventHandler;
import org.loomdev.api.event.EventOrder;

public class LoomEventSubscriber<E extends Event> implements EventSubscriber<E> {

    final EventHandler<E> handler;
    final int postOrder;

    public LoomEventSubscriber(EventHandler<E> handler, EventOrder postOrder) {
        this.handler = handler;
        this.postOrder = postOrder.ordinal();
    }

    @Override
    public void invoke(@NonNull E event) {
        this.handler.execute(event);
    }

    @Override
    public int postOrder() {
        return this.postOrder;
    }
}
