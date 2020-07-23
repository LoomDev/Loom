package org.loomdev.loom.event;

import com.google.common.base.Preconditions;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import net.kyori.event.PostResult;
import net.kyori.event.method.MethodSubscriptionAdapter;
import net.kyori.event.method.SimpleMethodSubscriptionAdapter;
import net.kyori.event.method.asm.ASMEventExecutorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.loomdev.api.event.Event;
import org.loomdev.api.event.EventHandler;
import org.loomdev.api.event.EventManager;
import org.loomdev.api.event.EventOrder;
import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.loom.plugin.loader.PluginClassLoader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventManagerImpl implements EventManager {

    private static final Logger LOGGER = LogManager.getLogger("Event Manager");

    private final ListMultimap<Object, Object> registeredListenersByPlugin;
    private final ListMultimap<Object, EventHandler<? extends Event>> registeredHandlersByPlugin;
    private final PluginManager pluginManager;
    private final LoomEventBus bus;
    private final MethodSubscriptionAdapter<Object> methodAdapter;
    private final ExecutorService executorService;

    public EventManagerImpl(PluginManager pluginManager) {
        this.pluginManager = pluginManager;

        // Expose the event executors to the plugins - required in order for the generated ASM classes
        // to work.
        PluginClassLoader cl = new PluginClassLoader(new URL[0]);
        cl.addToClassloaders();

        this.registeredListenersByPlugin = Multimaps.synchronizedListMultimap(Multimaps.newListMultimap(new IdentityHashMap<>(), ArrayList::new));
        this.registeredHandlersByPlugin = Multimaps.synchronizedListMultimap(Multimaps.newListMultimap(new IdentityHashMap<>(), ArrayList::new));

        this.bus = new LoomEventBus();
        this.methodAdapter = new SimpleMethodSubscriptionAdapter<>(
                bus,
                new ASMEventExecutorFactory<>(cl),
                new LoomMethodScanner());

        this.executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors(), // TODO configurable in loom.yml
                new ThreadFactoryBuilder()
                    .setNameFormat("Loom Event Executor #%d")
                    .setDaemon(true)
                    .build());
    }

    @Override
    public void register(Plugin plugin, Object listener) {
        Preconditions.checkNotNull(listener);
        if (plugin == listener && registeredListenersByPlugin.containsEntry(plugin, plugin)) {
            throw new IllegalArgumentException("The plugin main instance is automatically registered.");
        }

        registeredListenersByPlugin.put(plugin, listener);
        methodAdapter.register(listener);;
    }

    @Override
    public <E extends Event> void register(Plugin plugin, Class<E> eventClass, EventOrder eventOrder, EventHandler<E> eventHandler) {
        Preconditions.checkNotNull(eventClass);
        Preconditions.checkNotNull(eventOrder);
        Preconditions.checkNotNull(eventHandler);

        registeredHandlersByPlugin.put(plugin, eventHandler);
        bus.register(eventClass, new LoomEventSubscriber<E>(eventHandler, eventOrder));
    }

    @Override
    public void unregister(Plugin plugin) {
        Collection<Object> listeners = registeredListenersByPlugin.removeAll(plugin);
        listeners.forEach(methodAdapter::unregister);
        Collection<EventHandler<?>> handlers = registeredHandlersByPlugin.removeAll(plugin);
        handlers.forEach(this::unregister);
    }

    @Override
    public void unregister(Plugin plugin, Object listener) {
        Preconditions.checkNotNull(listener, "listener");
        if (registeredListenersByPlugin.remove(plugin, listener)) {
            methodAdapter.unregister(listener);
        }
    }

    @Override
    public <E extends Event> void unregister(EventHandler<E> handler) {
        bus.unregister(s -> s instanceof LoomEventSubscriber && ((LoomEventSubscriber<?>) s).handler == handler);
    }

    @Override
    public <E extends Event> void unregister(Plugin plugin, EventHandler<E> handler) {
        Preconditions.checkNotNull(handler, "listener");
        if (registeredHandlersByPlugin.remove(plugin, handler)) {
            unregister(handler);
        }
    }

    @Override
    public <E extends Event> E fire(E event) {
        Preconditions.checkNotNull(event);

        if (!bus.hasSubscribers(event.getClass())) {
            return event;
        }

        fireEvent(event);
        return event;
    }

    @Override
    public <E extends Event> CompletableFuture<E> fireAsync(E event) {
        Preconditions.checkNotNull(event);

        if (!bus.hasSubscribers(event.getClass())) {
            return CompletableFuture.completedFuture(event);
        }

        CompletableFuture<E> eventFuture = new CompletableFuture<>();
        executorService.execute(() -> {
            fireEvent(event);
            eventFuture.complete(event);
        });
        return eventFuture;
    }

    @Override
    public <E extends Event> void fireAndForgetAsync(E event) {
        Preconditions.checkNotNull(event);

        if (!bus.hasSubscribers(event.getClass())) {
            return;
        }
        executorService.execute(() -> fireEvent(event));
    }

    private <E extends Event> void fireEvent(E event) {
        PostResult result = bus.post(event);
        if (!result.exceptions().isEmpty()) {
            LOGGER.error("Some errors occurred whilst posting event {}.", event);
            int i = 0;
            for (Throwable exception : result.exceptions().values()) {
                LOGGER.error("#{}: \n", ++i, exception);
            }
        }
    }
}
