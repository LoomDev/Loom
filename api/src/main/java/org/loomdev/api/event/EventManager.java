package org.loomdev.api.event;

import java.util.concurrent.CompletableFuture;

public interface EventManager {

    /**
     * Registers all methods in the specified listener marked with the {@link Subscribe} annotation.
     *
     * @param plugin The plugin.
     * @param listener The listener.
     */
    void register(Object plugin, Object listener);

    /**
     * Registers an event handler.
     *
     * @param plugin The plugin.
     * @param eventClass The event class to listen to.
     * @param handler The event handler.
     * @param <E> The event type.
     * @param handler the event handler
     */
    default <E extends Event> void register(Object plugin, Class<E> eventClass, EventHandler<E> handler) {
        register(plugin, eventClass, EventOrder.NORMAL, handler);
    }

    /**
     * Registers an event handler.
     *
     * @param plugin The plugin.
     * @param eventClass The event class to listen to.
     * @param order The event order.
     * @param handler The event handler.
     * @param <E> The event type.
     */
    <E extends Event> void register(Object plugin, Class<E> eventClass, EventOrder order, EventHandler<E> handler);

    /**
     * Unregisters all event handlers linked to a plugin.
     *
     * @param plugin The plugin.
     */
    void unregister(Object plugin);

    /**
     * Unregisters a listener.
     *
     * @param plugin The plugin.
     * @param listener The listener.
     */
    void unregister(Object plugin, Object listener);

    /**
     * Unregisters an event handler
     *
     * @param handler The event handler.
     * @param <E> The event type.
     */
    <E extends Event> void unregister(EventHandler<E> handler);

    /**
     * Unregisters an event handler
     *
     * @param plugin the Plugin
     * @param handler The handler.
     * @param <E> The event type.
     */
    <E extends Event> void unregister(Object plugin, EventHandler<E> handler);

    /**
     * Fires an event.
     *
     * @param event The event.
     * @return The same event.
     * @param <E> The event type.
     */
    <E extends Event> E fire(E event);

    /**
     * Fires an event asyncronously.
     *
     * @param event The event.
     * @return A completable future completed when the event is fired.
     * @param <E> The event type.
     */
    <E extends Event> CompletableFuture<E> fireAsync(E event);

    /**
     * Fires an event without returning any changes.
     *
     * @param event The event.
     * @param <E> The event type.
     */
    default <E extends Event> void fireAndForget(E event) {
        fire(event);
    }

    /**
     * Fires an event without returning any changes asyncronously.
     *
     * @param event The event.
     * @param <E> The event type.
     */
    default <E extends Event> void fireAndForgetAsync(E event) {
        fireAsync(event);
    }
}
