package org.loomdev.loom.plugin.loader.java;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.loomdev.api.plugin.*;
import org.loomdev.api.plugin.ap.SerializedPluginMetadata;
import org.loomdev.loom.plugin.PluginClassLoader;
import org.loomdev.loom.plugin.loader.LoomPluginMetadata;
import org.loomdev.loom.server.LoomServer;

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

public class JavaPluginLoader implements PluginLoader {

    private final LoomServer loomServer;
    private final Path pluginDirectory;

    public JavaPluginLoader(LoomServer loomServer, Path pluginDirectory) {
        this.loomServer = loomServer;
        this.pluginDirectory = pluginDirectory;
    }

    @Override
    public PluginMetadata loadPlugin(Path path) throws Exception {
        Optional<SerializedPluginMetadata> serialized = getSerializedPluginInfo(path);

        if (!serialized.isPresent()) {
            throw new InvalidPluginException("Did not find a valid loom-plugin.json.");
        }

        SerializedPluginMetadata pd = serialized.get();
        if (!SerializedPluginMetadata.ID_PATTERN.matcher(pd.getId()).matches()) {
            throw new InvalidPluginException("Plugin ID '" + pd.getId() + "' is invalid.");
        }

        PluginClassLoader loader = new PluginClassLoader(new URL[] { path.toUri().toURL() });
        loader.addToClassloaders();
        Class<?> mainClass = loader.loadClass(pd.getMain());

        if (!Plugin.class.isAssignableFrom(mainClass)) {
            throw new InvalidPluginException("The main class should implement the Plugin interface.");
        }
        return createDescription(pd, path, mainClass);
    }

    @Override
    public Plugin createPlugin(PluginMetadata pluginMetadata) {
        if (!(pluginMetadata instanceof JavaLoomPluginMetadata)) {
            throw new IllegalArgumentException("Description provided isn't of the Java plugin loader");
        }

        JavaLoomPluginMetadata javaDescription = (JavaLoomPluginMetadata) pluginMetadata;
        Optional<Path> source = javaDescription.getSource();

        if (!source.isPresent()) {
            throw new IllegalArgumentException("No path in plugin description");
        }

        Injector injector = Guice.createInjector(new PluginInjectorModule(this.loomServer, javaDescription, this.pluginDirectory));
        Plugin instance = (Plugin) injector.getInstance(javaDescription.getMainClass());

        if (instance == null) {
            throw new IllegalStateException(
                    "Got nothing from injector for plugin " + javaDescription.getId());
        }

        return instance;
    }

    private Optional<SerializedPluginMetadata> getSerializedPluginInfo(Path source)
            throws Exception {
        boolean foundBungeeBukkitPluginFile = false;
        try (JarInputStream in = new JarInputStream(
                new BufferedInputStream(Files.newInputStream(source)))) {
            JarEntry entry;
            while ((entry = in.getNextJarEntry()) != null) {
                if (entry.getName().equals("loom-plugin.json")) {
                    try (Reader pluginInfoReader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
                        return Optional.of(LoomServer.GSON
                                .fromJson(pluginInfoReader, SerializedPluginMetadata.class));
                    }
                }

                if (entry.getName().equals("plugin.yml") || entry.getName().equals("bungee.yml")) {
                    foundBungeeBukkitPluginFile = true;
                }
            }

            if (foundBungeeBukkitPluginFile) {
                throw new InvalidPluginException("The plugin file " + source.getFileName() + " appears to "
                        + "be a Bukkit or BungeeCord plugin. Velocity does not support Bukkit or BungeeCord "
                        + "plugins.");
            }

            return Optional.empty();
        }
    }

    private LoomPluginMetadata createDescription(SerializedPluginMetadata description, Path source, Class mainClass) {
        return new JavaLoomPluginMetadata(
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
