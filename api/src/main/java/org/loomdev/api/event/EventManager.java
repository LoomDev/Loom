package org.loomdev.api.event;

import java.util.concurrent.CompletableFuture;

public interface EventManager {

    void register(Object plugin, Object listener);

    default <E extends Event> void register(Object plugin, Class<E> eventClass, EventHandler<E> handler) {
        register(plugin, eventClass, EventOrder.NORMAL, handler);
    }

    <E extends Event> void register(Object plugin, Class<E> eventClass, EventOrder order, EventHandler<E> handler);

    void unregister(Object plugin);

    void unregister(Object plugin, Object listener);

    <E extends Event> void unregister(EventHandler<E> handler);

    <E extends Event> void unregister(Object plugin, EventHandler<E> handler);

    <E extends Event> E fire(E event);

    <E extends Event>CompletableFuture<E> fireAsync(E event);

    default <E extends Event> void fireAndForget(E event) {
        fire(event);
    }

    default <E extends Event> void fireAndForgetAsync(E event) {
        fireAsync(event);
    }
}
