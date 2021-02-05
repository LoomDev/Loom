package org.loomdev.api.event;

import org.loomdev.api.plugin.exception.IllegalPluginStateException;

import java.util.concurrent.CompletableFuture;

public interface EventManager {

    /**
     * Registers all methods in the specified listener marked with the {@link Subscribe} annotation.
     *
     * @param listener The listener.
     * @throws IllegalPluginStateException when the plugin is not in the {@link org.loomdev.api.plugin.PluginState#ENABLING} or {@link org.loomdev.api.plugin.PluginState#ENABLED} state.
     */
    void registerListener(Object listener) throws IllegalPluginStateException;

    /**
     * Registers an event handler.
     *
     * @param eventClass The event class to listen to.
     * @param handler The event handler.
     * @param <E> The event type.
     * @throws IllegalPluginStateException when the plugin is not in the {@link org.loomdev.api.plugin.PluginState#ENABLING} or {@link org.loomdev.api.plugin.PluginState#ENABLED} state.
     */
    default <E extends Event> void registerListener(Class<E> eventClass, EventHandler<E> handler) throws IllegalPluginStateException {
        registerListener(eventClass, EventOrder.NORMAL, handler);
    }

    /**
     * Registers an event handler.
     *
     * @param eventClass The event class to listen to.
     * @param order The event order.
     * @param handler The event handler.
     * @param <E> The event type.
     * @throws IllegalPluginStateException when the plugin is not in the {@link org.loomdev.api.plugin.PluginState#ENABLING} or {@link org.loomdev.api.plugin.PluginState#ENABLED} state.
     */
    <E extends Event> void registerListener(Class<E> eventClass, EventOrder order, EventHandler<E> handler) throws IllegalPluginStateException;

    /**
     * Unregisters all event handlers linked to a plugin.
     */
    void unregisterAll();

    /**
     * Unregisters a listener.
     *
     * @param listener The listener.
     */
    void unregisterListener(Object listener);

    /**
     * Unregisters an event handler
     *
     * @param handler The event handler.
     * @param <E> The event type.
     */
    <E extends Event> void unregisterListener(EventHandler<E> handler);

    /**
     * Fires an event.
     *
     * @param event The event.
     * @return The same event.
     * @param <E> The event type.
     * @throws IllegalPluginStateException when the plugin is not in the {@link org.loomdev.api.plugin.PluginState#ENABLING} or {@link org.loomdev.api.plugin.PluginState#ENABLED} state.
     */
    <E extends Event> E fireEvent(E event) throws IllegalPluginStateException;

    /**
     * Fires an event asyncronously.
     *
     * @param event The event.
     * @return A completable future completed when the event is fired.
     * @param <E> The event type.
     * @throws IllegalPluginStateException when the plugin is not in the {@link org.loomdev.api.plugin.PluginState#ENABLING} or {@link org.loomdev.api.plugin.PluginState#ENABLED} state.
     */
    <E extends Event> CompletableFuture<E> fireEventAsync(E event) throws IllegalPluginStateException;

    /**
     * Fires an event without returning any changes.
     *
     * @param event The event.
     * @param <E> The event type.
     * @throws IllegalPluginStateException when the plugin is not in the {@link org.loomdev.api.plugin.PluginState#ENABLING} or {@link org.loomdev.api.plugin.PluginState#ENABLED} state.
     */
    default <E extends Event> void fireAndForgetEvent(E event) throws IllegalPluginStateException {
        fireEvent(event);
    }

    /**
     * Fires an event without returning any changes asynchronously.
     *
     * @param event The event.
     * @param <E> The event type.
     * @throws IllegalPluginStateException when the plugin is not in the {@link org.loomdev.api.plugin.PluginState#ENABLING} or {@link org.loomdev.api.plugin.PluginState#ENABLED} state.
     */
    default <E extends Event> void fireAndForgetEventAsync(E event) throws IllegalPluginStateException {
        fireEventAsync(event);
    }
}
