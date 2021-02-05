package org.loomdev.loom.plugin.loader.injector.providers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.loomdev.api.config.Configuration;
import org.loomdev.api.config.ConfigurationException;
import org.loomdev.api.config.file.YamlConfiguration;
import org.loomdev.api.plugin.Config;
import org.loomdev.api.plugin.DataDirectory;
import org.loomdev.loom.plugin.loader.injector.MainPluginClass;
import org.loomdev.loom.plugin.loader.injector.injectionpoint.InjectionPoint;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigurationProvider implements Provider<Configuration> {

    @Inject @MainPluginClass
    Class<?> pluginClass;

    @Inject @DataDirectory
    Path pluginFolder;

    @Inject
    InjectionPoint injectionPoint;

    @Override
    public Configuration get() {
        var config = injectionPoint.getAnnotation(Config.class);
        var configFile = pluginFolder.resolve(config.path()).toFile();

        if (!configFile.exists()) {
            if (!config.copyDefault()) {
                throw new ConfigurationException("Unable to find file " + configFile.getAbsolutePath());
            }

            String defaultPath = config.resourcePath().trim().equalsIgnoreCase("") ? config.path() : config.resourcePath();
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
            throw new ConfigurationException("Unable to create configuration file for " + config.toString());
        }

        return configuration;
    }
}
