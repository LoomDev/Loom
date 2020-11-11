package org.loomdev.loom.plugin.loader;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.ApiVersion;
import org.loomdev.api.plugin.PluginContainer;
import org.loomdev.api.plugin.PluginMetadata;
import org.loomdev.api.plugin.ap.SerializedPluginMetadata;
import org.loomdev.loom.plugin.data.LoomPluginContainer;
import org.loomdev.loom.plugin.data.LoomPluginMetadata;
import org.loomdev.loom.plugin.loader.injector.InjectionPointProvider;
import org.loomdev.loom.plugin.loader.injector.PluginInjectorModule;
import org.loomdev.loom.plugin.loader.injector.PluginLoadingModule;
import org.loomdev.loom.server.ServerImpl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.stream.Collectors;

public class PluginLoaderImpl {

    private static final Logger LOGGER = LogManager.getLogger("PluginLoader");

    private final ServerImpl server;
    private final Path pluginDirectory;

    public PluginLoaderImpl(ServerImpl server, Path pluginDirectory) {
        this.server = server;
        this.pluginDirectory = pluginDirectory;
    }

    public @NotNull Optional<PluginMetadata> loadMetadata(@NotNull Path source) {
        Optional<SerializedPluginMetadata> serialized = getSerializedPluginInfo(source);
        if (!serialized.isPresent()) {
            LOGGER.warn("Plugin file '{}' doesn't contain a loom.json file.", source.getFileName());
            return Optional.empty();
        }

        SerializedPluginMetadata data = serialized.get();
        if (!SerializedPluginMetadata.ID_PATTERN.matcher(data.getId()).matches()) {
            LOGGER.error("Plugin id '{}' is invalid. The id can only contain alphanumerical characters, dashed, underlines and can max. be 64 characters long.", data.getId());
            return Optional.empty();
        }

        String nameOrId = Optional.ofNullable(data.getName()).orElse(data.getId());

        ApiVersion minimumApiVersion;
        try {
            minimumApiVersion = ApiVersion.valueOf(data.getMinimumApiVersion());
        } catch (IllegalArgumentException e) {
            LOGGER.warn("This server cannot read the minimum required api version of '{}'. This probably means that the server is outdated and cannot load the plugin.", nameOrId);
            return Optional.empty();
        }

        if (server.getApiVersion().isOlderThan(minimumApiVersion)) {
            LOGGER.warn("Plugin '{}' requires a newer version of Loom-API to run. (Current version: {}, Required version: {})", nameOrId, server.getApiVersion().getName(), minimumApiVersion.getName());
            return Optional.empty();
        }

        return Optional.of(createMetadata(data, source));
    }

    public @NotNull Optional<PluginContainer> loadPlugin(@NotNull PluginMetadata metadata) {
        if (!(metadata instanceof LoomPluginMetadata)) {
            throw new IllegalArgumentException("Invalid plugin metadata.");
        }

        try {
            PluginClassLoader loader = new PluginClassLoader(new URL[] { metadata.getSource().toUri().toURL() });
            loader.addToClassloaders();
            Class<?> mainClass = loader.loadClass(metadata.getMain());

            Injector injector = Guice.createInjector(new InjectionPointProvider(), new PluginLoadingModule(mainClass), new PluginInjectorModule(this.server, (LoomPluginMetadata) metadata, this.pluginDirectory));
            Object instance = injector.getInstance(mainClass);

            if (instance == null) {
                throw new IllegalStateException(String.format("Got nothing from injector for %s.", metadata.getId()));
            }

            return Optional.of(new LoomPluginContainer(metadata, instance, loader));
        } catch (MalformedURLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

//    @Override
//    public PluginContainer loadPlugin(Path path) {
//        Optional<SerializedPluginMetadata> serialized = getSerializedPluginInfo(path);
//        if (!serialized.isPresent()) {
//            LOGGER.warn("Plugin file '{}' doesn't contain a loom.json file.", path.getFileName());
//            return null;
//        }
//
//        SerializedPluginMetadata data = serialized.get();
//        if (!SerializedPluginMetadata.ID_PATTERN.matcher(data.getId()).matches()) {
//            LOGGER.error("Plugin id '{}' is invalid. The id can only contain alphanumerical characters, dashed, underlines and can max. be 64 characters long.", data.getId());
//            return null;
//        }
//
//        String nameOrId = Optional.ofNullable(data.getName()).orElse(data.getId());
//
//        ApiVersion minimumApiVersion;
//        try {
//            minimumApiVersion = ApiVersion.valueOf(data.getMinimumApiVersion());
//        } catch (IllegalArgumentException e) {
//            LOGGER.warn("This server cannot read the minimum required api version of '{}'. This probably means that the server is outdated and cannot load the plugin.", nameOrId);
//            return null;
//        }
//
//        if (server.getApiVersion().isOlderThan(minimumApiVersion)) {
//            LOGGER.warn("Plugin '{}' requires a newer version of Loom-API to run. (Current version: {}, Required version: {})", nameOrId, server.getApiVersion().getName(), minimumApiVersion.getName());
//            return null;
//        }
//
//        try {
//            PluginClassLoader loader = new PluginClassLoader(new URL[] { path.toUri().toURL() });
//            loader.addToClassloaders();
//            Class<?> mainClass = loader.loadClass(data.getMain());
//
//            if (!Plugin.class.isAssignableFrom(mainClass)) {
//                LOGGER.error("Main class of '{}' does not implement the Plugin interface.", nameOrId);
//                return null;
//            }
//
//            return new LoomPluginContainer(createMetadata(data, path, mainClass), null, loader);
//        } catch (Exception ex) {
//            LOGGER.error("Something went wrong attempting to load {}.", nameOrId, ex);
//        }
//        return null;
//    }
//
//    @Override
//    public Plugin createPlugin(PluginMetadata pluginMetadata) {
//        if (!(pluginMetadata instanceof LoomPluginMetadata)) {
//            throw new IllegalArgumentException("Invalid plugin metadata.");
//        }
//
//        LoomPluginMetadata metadata = (LoomPluginMetadata) pluginMetadata;
//        Optional<Path> source = metadata.getSource();
//
//        if (!source.isPresent()) {
//            throw new IllegalArgumentException("No path present in plugin metadata.");
//        }
//
//        Injector injector = Guice.createInjector(new InjectionPointProvider(), new PluginInjectorModule(this.server, metadata, this.pluginDirectory));
//        Plugin instance = (Plugin) injector.getInstance(metadata.getMainClass());
//
//        if (instance == null) {
//            throw new IllegalStateException(String.format("Got nothing from injector for %s.", metadata.getId()));
//        }
//
//        return instance;
//    }

    private Optional<SerializedPluginMetadata> getSerializedPluginInfo(Path source) {
        try (JarInputStream in = new JarInputStream(new BufferedInputStream(Files.newInputStream(source)))) {
            JarEntry entry;
            while ((entry = in.getNextJarEntry()) != null) {
                if (entry.getName().equals("loom.json")) {
                    try (Reader pluginInfoReader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
                        return Optional.of(ServerImpl.GSON.fromJson(pluginInfoReader, SerializedPluginMetadata.class));
                    }
                }

            }
        } catch (IOException e) {
            LOGGER.error("An error occurred attempting to read '{}'.", source.getFileName());
        }
        return Optional.empty();
    }

    private LoomPluginMetadata createMetadata(SerializedPluginMetadata description, Path source) {
        return new LoomPluginMetadata(
                description.getId(),
                description.getName(),
                description.getVersion(),
                description.getDescription(),
                description.getAuthors(),
                description.getDependencies().stream()
                        .map(d -> new PluginMetadata.PluginDependency(d.getId(), d.isOptional()))
                        .collect(Collectors.toList()),
                ApiVersion.valueOf(description.getMinimumApiVersion()),
                source,
                description.getMain()
        );
    }
}
