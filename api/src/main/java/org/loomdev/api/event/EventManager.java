package org.loomdev.api.event;

import java.util.concurrent.CompletableFuture;

public interface EventManager {

    /**
     * Register all methods in the specified listener marked with the {@link Subscribe Subscribe} annotation.
     *
     * @param plugin The plugin.
     * @param listener The listener.
     */
    void register(Object plugin, Object listener);

    /**
     * Register an event handler
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
     * Register an event handler
     *
     * @param plugin The plugin.
     * @param eventClass The event class to listen to.
     * @param order The event order.
     * @param handler The event handler.
     * @param <E> The event type.
     * @param eventOrder The event order.
     * @param handler the event handler
     */
    <E extends Event> void register(Object plugin, Class<E> eventClass, EventOrder order, EventHandler<E> handler);

    /**
     * Unregister all event handlers linked to a plugin.
     *
     * @param plugin The plugin.
     */
    void unregister(Object plugin);

    /**
     * Unregister a listener.
     *
     * @param plugin The plugin.
     * @param listener The listener.
     */
    void unregister(Object plugin, Object listener);

    /**
     * Unregister an event handler
     *
     * @param handler The event handler.
     * @param <E> The event type.
     * @param handler The handler.
     */
    <E extends Event> void unregister(EventHandler<E> handler);

    /**
     * Unregister an event handler
     *
     * @param plugin The plugin
     * @param handler The event handler.
     * @param <E> The event type.
     * @param plugin the Plugin
     * @param handler The handler.
     */
    <E extends Event> void unregister(Object plugin, EventHandler<E> handler);

    /**
     * Fire an event.
     *
     * @param event The event.
     * @return The same event.
     * @param <E> The event type.
     */
    <E extends Event> E fire(E event);

    /**
     * Fire an event asyncronously.
     *
     * @param event The event.
     * @return A completable future completed when the event is fired.
     * @param <E> The event type.
     */
    <E extends Event> CompletableFuture<E> fireAsync(E event);

    /**
     * Fire an event.
     *
     * @param event The event.
     * @param <E> The event type.
     */
    default <E extends Event> void fireAndForget(E event) {
        fire(event);
    }

    /**
     * Fire an event asyncronously.
     *
     * @param event The event.
     * @param <E> The event type.
     */
    default <E extends Event> void fireAndForgetAsync(E event) {
        fireAsync(event);
    }
}
