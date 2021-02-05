package org.loomdev.loom.plugin;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.plugin.PluginState;
import org.loomdev.api.plugin.exception.IllegalPluginStateChangeException;
import org.loomdev.api.plugin.exception.PluginException;
import org.loomdev.api.plugin.lifecycle.LifecycleEvent;
import org.loomdev.api.plugin.lifecycle.LifecycleHook;
import org.loomdev.api.plugin.metadata.PluginMetadata;
import org.loomdev.api.server.Server;
import org.loomdev.loom.plugin.loader.PluginClassLoader;
import org.loomdev.loom.plugin.metadata.PluginMetadataImpl;
import org.loomdev.loom.server.ProxiedServer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PluginContainer {

    private @NotNull final PluginMetadataImpl metadata;
    private @NotNull final Path jarPath;
    private @NotNull PluginState state;
    private final ProxiedServer proxiedServer;

    private PluginClassLoader classLoader;
    private Object instance;

    private Map<Class<?>, Method> lifecycleHooks;

    public PluginContainer(@NotNull PluginMetadataImpl metadata, @NotNull Path jarPath, ProxiedServer server) {
        this.metadata = metadata;
        this.jarPath = jarPath;
        this.state = PluginState.INVALID;
        this.proxiedServer = server;

        this.lifecycleHooks = new HashMap<>();
    }

    @NotNull
    public PluginMetadata getMetadata() {
        return metadata;
    }

    @NotNull
    public PluginState getState() {
        return state;
    }

    public void setState(@NotNull PluginState state) {
        if (!this.state.canChangeTo(state)) {
            throw new IllegalPluginStateChangeException(metadata.getId(), this.state, state);
        }
        this.state = state;
    }

    public void setClassLoader(PluginClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @NotNull
    public Server getProxiedServer() {
        return proxiedServer;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
        this.findLifecycleEventHandlers();
    }

    @NotNull
    public Path getJarPath() {
        return jarPath;
    }

    public <TEvent extends LifecycleEvent> void fireLifecycleEvent(TEvent event) {
        if (instance == null) return;

        var handler = this.lifecycleHooks.get(event.getClass().getInterfaces()[0]);
        if (handler == null) {
            return;
        }

        try {
            handler.invoke(instance, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void findLifecycleEventHandlers() {
        if (instance == null) return;

        var mainClass = instance.getClass();
        this.lifecycleHooks = Arrays.stream(mainClass.getMethods())
                .filter(this::isValidLifecycleHookMethod)
                .collect(Collectors.toMap(method -> method.getParameterTypes()[0], method -> method));
    }

    private boolean isValidLifecycleHookMethod(Method method) {
        if (!method.isAnnotationPresent(LifecycleHook.class)) {
            return false;
        }

        var parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != 1) {
            return false;
        }

        if (!LifecycleEvent.class.isAssignableFrom(parameterTypes[0])) {
            return false;
        }

        return true;
    }

    public void dispose() throws IOException {
        if (classLoader != null) {
            classLoader.close();
            classLoader = null;
        }
        this.lifecycleHooks.clear();
        setInstance(null);
    }
}
