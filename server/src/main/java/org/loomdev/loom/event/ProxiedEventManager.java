package org.loomdev.loom.event;

import org.loomdev.api.event.Event;
import org.loomdev.api.event.EventHandler;
import org.loomdev.api.event.EventManager;
import org.loomdev.api.event.EventOrder;
import org.loomdev.api.plugin.PluginState;
import org.loomdev.api.plugin.exception.IllegalPluginStateException;
import org.loomdev.api.plugin.metadata.PluginMetadata;
import org.loomdev.loom.server.ServerImpl;

import java.util.concurrent.CompletableFuture;

public class ProxiedEventManager implements EventManager {

    private final PluginMetadata metadata;
    private final ServerImpl internalServer;
    private final EventManagerImpl internalEventManager;

    public ProxiedEventManager(PluginMetadata metadata, ServerImpl internalServer) {
        this.metadata = metadata;
        this.internalServer = internalServer;
        this.internalEventManager = internalServer.getEventManager();
    }

    private void checkState() {
        var optionalContainer = internalServer.getPluginManager().getPlugins().getContainer(metadata.getId());
        if (optionalContainer.isEmpty()) {
            throw new IllegalPluginStateException(metadata.getId());
        }

        var container = optionalContainer.get();
        var state = container.getState();
        if (state != PluginState.ENABLING && state != PluginState.ENABLED) {
            throw new IllegalPluginStateException(metadata.getId());
        }
    }

    @Override
    public void registerListener(Object listener) throws IllegalPluginStateException {
        checkState();
        internalEventManager.register(metadata, listener);
    }

    @Override
    public <E extends Event> void registerListener(Class<E> eventClass, EventOrder order, EventHandler<E> handler) throws IllegalPluginStateException {
        checkState();
        internalEventManager.register(metadata, eventClass, order, handler);
    }

    @Override
    public void unregisterAll() {
        internalEventManager.unregister(metadata);
    }

    @Override
    public void unregisterListener(Object listener) {
        internalEventManager.unregister(metadata, listener);
    }

    @Override
    public <E extends Event> void unregisterListener(EventHandler<E> handler) {
        internalEventManager.unregister(handler);
    }

    @Override
    public <E extends Event> E fireEvent(E event) throws IllegalPluginStateException {
        checkState();
        return internalEventManager.fire(event);
    }

    @Override
    public <E extends Event> CompletableFuture<E> fireEventAsync(E event) throws IllegalPluginStateException {
        checkState();
        return internalEventManager.fireAsync(event);
    }
}
