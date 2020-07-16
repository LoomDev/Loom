package org.loomdev.loom.plugin.loader;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.loomdev.api.plugin.*;
import org.loomdev.api.plugin.ap.SerializedPluginMetadata;
import org.loomdev.loom.plugin.data.LoomPluginContainer;
import org.loomdev.loom.plugin.data.LoomPluginMetadata;
import org.loomdev.loom.plugin.loader.injector.InjectionPointProvider;
import org.loomdev.loom.plugin.loader.injector.PluginInjectorModule;
import org.loomdev.loom.server.ServerImpl;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class PluginLoaderImpl implements PluginLoader {

    private final ServerImpl server;
    private final Path pluginDirectory;

    public PluginLoaderImpl(ServerImpl server, Path pluginDirectory) {
        this.server = server;
        this.pluginDirectory = pluginDirectory;
    }

    @Override
    public PluginContainer loadPlugin(Path path) throws Exception {
        Optional<SerializedPluginMetadata> serialized = getSerializedPluginInfo(path);

        if (!serialized.isPresent()) {
            throw new InvalidPluginException("Did not find a valid loom-plugin.json.");
        }

        SerializedPluginMetadata data = serialized.get();
        if (!SerializedPluginMetadata.ID_PATTERN.matcher(data.getId()).matches()) {
            throw new InvalidPluginException(String.format("Plugin ID '%s' is invalid.", data.getId()));
        }

        PluginClassLoader loader = new PluginClassLoader(new URL[] { path.toUri().toURL() });
        loader.addToClassloaders();
        Class<?> mainClass = loader.loadClass(data.getMain());

        if (!Plugin.class.isAssignableFrom(mainClass)) {
            throw new InvalidPluginException("Main class does not implement Plugin.");
        }

        return new LoomPluginContainer(createMetadata(data, path, mainClass), null, loader);
    }

    @Override
    public Plugin createPlugin(PluginMetadata pluginMetadata) {
        if (!(pluginMetadata instanceof LoomPluginMetadata)) {
            throw new IllegalArgumentException("Invalid plugin metadata.");
        }

        LoomPluginMetadata metadata = (LoomPluginMetadata) pluginMetadata;
        Optional<Path> source = metadata.getSource();

        if (!source.isPresent()) {
            throw new IllegalArgumentException("No path present in plugin metadata.");
        }

        // Injector injector = Guice.createInjector(new PluginInjectorModule(this.server, metadata, this.pluginDirectory));
        Injector injector = Guice.createInjector(new InjectionPointProvider(), new PluginInjectorModule(this.server, metadata, this.pluginDirectory));
        Plugin instance = (Plugin) injector.getInstance(metadata.getMainClass());

        if (instance == null) {
            throw new IllegalStateException(String.format("Got nothing from injector for %s.", metadata.getId()));
        }

        return instance;
    }

    private Optional<SerializedPluginMetadata> getSerializedPluginInfo(Path source) throws Exception {
        boolean bukkit = false;
        try (JarInputStream in = new JarInputStream(new BufferedInputStream(Files.newInputStream(source)))) {
            JarEntry entry;
            while ((entry = in.getNextJarEntry()) != null) {
                if (entry.getName().equals("loom.json")) {
                    try (Reader pluginInfoReader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
                        return Optional.of(ServerImpl.GSON.fromJson(pluginInfoReader, SerializedPluginMetadata.class));
                    }
                }

                if (entry.getName().equals("plugin.yml")) {
                   bukkit = true;
                }
            }

            if (bukkit) {
                throw new InvalidPluginException(String.format("%s appears to be a Bukkit plugin.", source.getFileName()));
            }
            return Optional.empty();
        }
    }

    private LoomPluginMetadata createMetadata(SerializedPluginMetadata description, Path source, Class<?> mainClass) {
        return new LoomPluginMetadata(
                description.getId(),
                description.getName(),
                description.getVersion(),
                description.getDescription(),
                description.getAuthors(),
                source,
                mainClass
        );
    }
}
