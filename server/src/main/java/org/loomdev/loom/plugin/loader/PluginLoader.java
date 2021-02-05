package org.loomdev.loom.plugin.loader;

import com.google.inject.Guice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.ApiVersion;
import org.loomdev.ap.plugin.PluginProcessor;
import org.loomdev.ap.plugin.SerializablePluginMetadata;
import org.loomdev.loom.Loom;
import org.loomdev.loom.plugin.PluginContainer;
import org.loomdev.api.plugin.exception.PluginLoadingException;
import org.loomdev.loom.plugin.loader.injector.PluginInjectorModule;
import org.loomdev.loom.plugin.loader.injector.injectionpoint.InjectionPointProvider;
import org.loomdev.loom.plugin.metadata.PluginDependencyImpl;
import org.loomdev.loom.plugin.metadata.PluginMetadataImpl;
import org.loomdev.loom.server.ProxiedServer;
import org.loomdev.loom.server.ServerImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.jar.JarFile;

public class PluginLoader {

    private static final Logger LOGGER = LogManager.getLogger("PluginLoader");
    private final ServerImpl internalServer;

    public PluginLoader(ServerImpl internalServer) {
        this.internalServer = internalServer;
    }

    @NotNull
    public Optional<PluginContainer> loadMetadataFromJar(@NotNull Path jarPath) {
        try (var jar = new JarFile(jarPath.toFile())){
            var loomEntry = jar.getJarEntry(PluginProcessor.LOOM_JAR_FILE);
            if (loomEntry == null) {
                LOGGER.warn("No '{}' file found in '{}'. Are you sure this a plugin?", PluginProcessor.LOOM_JAR_FILE, jarPath.getFileName());
                return Optional.empty();
            }

            var loomIs = jar.getInputStream(loomEntry);
            var serialized = Loom.YAML.loadAs(loomIs, SerializablePluginMetadata.class);

            if (serialized.id.equalsIgnoreCase("loom") || serialized.id.equalsIgnoreCase("minecraft")) {
                LOGGER.error("Invalid plugin id '{}' for '{}'. ('loom' and 'minecraft' are reserved namespaces that can not be used by plugins)", serialized.id, jarPath.getFileName());
                return Optional.empty();
            }

            var dependencies = mapDependencies(serialized.dependencies);
            var minimumApiVersion = ApiVersion.getByName(serialized.minimumApiVersion);
            if (minimumApiVersion == null) {
                minimumApiVersion = ApiVersion.UNKNOWN;
            }

            var metaData = new PluginMetadataImpl(
                    serialized.id,
                    serialized.name,
                    serialized.mainClass,
                    serialized.version,
                    serialized.description,
                    serialized.authors,
                    dependencies,
                    minimumApiVersion
            );

            if (minimumApiVersion == ApiVersion.UNKNOWN) {
                LOGGER.warn("Plugin '{}' has not specified a minimum api version. The plugin will be loaded, but may not run correctly.", metaData.getName());
            } else if (internalServer.getApiVersion().isOlderThan(minimumApiVersion)) {
                LOGGER.error("Plugin '{}' requires a never version of Loom-API to run. The plugin will NOT be loaded (Current version: {}, Required version: {})", metaData.getName(), internalServer.getApiVersion().getName(), minimumApiVersion.getName());
                return Optional.empty();
            }

            var container = new PluginContainer(metaData, jarPath, new ProxiedServer(metaData, internalServer));
            return Optional.of(container);
        } catch (IOException exception) {
            LOGGER.error("An error occurred attempting to read a plugin file. '{}'.\n{}", jarPath.getFileName(), exception);
            return Optional.empty();
        } catch (NullPointerException exception) {
            LOGGER.error("Property '{}' is required, but was not found in '{}' of '{}'.", exception.getMessage(), PluginProcessor.LOOM_JAR_FILE, jarPath.getFileName());
            return Optional.empty();
        }
    }

    public PluginContainer load(@NotNull PluginContainer container) throws PluginLoadingException {
        var metadata = container.getMetadata();

        try {
            var classLoader = new PluginClassLoader(container.getJarPath());
            container.setClassLoader(classLoader);

            var pluginDataDirectory = internalServer.getPluginDirectory().resolve(metadata.getId());

            var mainClass = classLoader.loadClass(metadata.getMainClass());
            var injector = Guice.createInjector(new InjectionPointProvider(), new PluginInjectorModule(metadata, container.getProxiedServer(), pluginDataDirectory, mainClass));
            var instance = injector.getInstance(mainClass);
            container.setInstance(instance);

            return container;
        } catch (Exception e) {
            LOGGER.error("Failed to load '{}'.\n{}", metadata.getId(), e);
            throw new PluginLoadingException(metadata.getId(), e);
        }
    }

    private static PluginDependencyImpl[] mapDependencies(SerializablePluginMetadata.Dependency[] dependencies) {
        return Arrays.stream(dependencies)
                .map(dependency -> new PluginDependencyImpl(
                        dependency.id,
                        dependency.version,
                        dependency.required
                ))
                .toArray(PluginDependencyImpl[]::new);
    }
}
