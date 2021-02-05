package org.loomdev.loom.plugin.loader;

import com.google.common.collect.Sets;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Set;

public class PluginClassLoader extends URLClassLoader {

    public static final InternalPluginLoader PLUGIN_LOADER = new InternalPluginLoader();

    public PluginClassLoader(@NotNull Path jarPath) throws MalformedURLException {
        super(new URL[] { jarPath.toUri().toURL() });
        PLUGIN_LOADER.pluginLoaders.add(this);
    }

    @Override
    public void close() throws IOException {
        PLUGIN_LOADER.pluginLoaders.remove(this);
        super.close();
    }

    private static class InternalPluginLoader extends URLClassLoader {

        private final Set<PluginClassLoader> pluginLoaders;

        public InternalPluginLoader() {
            super(new URL[0]);
            pluginLoaders = Sets.newCopyOnWriteArraySet();
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            for (var pluginLoader : pluginLoaders) {
                if (pluginLoader == null) continue;

                try {
                    return pluginLoader.loadClass(name, resolve);
                } catch (ClassNotFoundException ignored) {
                    // We're trying others, safe to ignore
                }
            }

            throw new ClassNotFoundException(name);
        }
    }

}
