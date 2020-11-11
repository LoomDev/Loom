package org.loomdev.loom.plugin.loader.injector.providers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.loomdev.api.config.Configuration;
import org.loomdev.api.config.ConfigurationException;
import org.loomdev.api.config.file.YamlConfiguration;
import org.loomdev.api.plugin.annotation.Config;
import org.loomdev.api.plugin.annotation.PluginDirectory;
import org.loomdev.loom.plugin.loader.injector.InjectionException;
import org.loomdev.loom.plugin.loader.injector.LoomInjectionPoint;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

// TODO needs some work
public class ConfigProvider implements Provider<Configuration> {

    @Inject
    Class<?> pluginClass;

    @Inject
    @PluginDirectory
    Path pluginFolder;

    @Inject
    LoomInjectionPoint injectionPoint;

    @Override
    public Configuration get() {
        Config config = injectionPoint.getAnnotation(Config.class);
        File configFile = pluginFolder.resolve(config.path()).toFile();

        if (!configFile.exists()) {
            if (!config.copyDefault()) {
                throw new InjectionException("Unable to find file " + configFile.getAbsolutePath());
            }

            String defaultPath = config.defaultPath().trim().equalsIgnoreCase("") ? config.path() : config.defaultPath();
            InputStream is = pluginClass.getClassLoader().getResourceAsStream(defaultPath);

            try {
                Files.createDirectories(configFile.toPath().getParent());
                Files.copy(is, configFile.toPath());
            } catch (IOException e) {
                throw new ConfigurationException("Couldn't copy the default configuration.", e);
            }
        }

        Configuration configuration = null;
        if (config.path().toLowerCase().endsWith(".yml") || config.path().toLowerCase().endsWith(".yaml")) {
            try {
                configuration = YamlConfiguration.fromFile(configFile);
            } catch (IOException e) {
                throw new ConfigurationException(e);
            }
        }

        if (configuration == null) {
            throw new InjectionException("Unable to create configuration file for " + config.toString());
        }

        return configuration;
    }
}
