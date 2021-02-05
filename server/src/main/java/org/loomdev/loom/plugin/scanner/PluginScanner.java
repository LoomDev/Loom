package org.loomdev.loom.plugin.scanner;

import com.google.common.collect.ImmutableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.loomdev.loom.plugin.loader.PluginLoader;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class PluginScanner {

    private static final Logger LOGGER = LogManager.getLogger("PluginScanner");
    private static final PathMatcher JAR_MATCHER = FileSystems.getDefault().getPathMatcher("glob:plugins/*.jar");

    private final Path pluginFolder;
    private final PluginFileFilter pluginFileFilter;

    public PluginScanner(@NotNull Path pluginFolder) {
        this.pluginFolder = pluginFolder;
        this.pluginFileFilter = new PluginFileFilter();
    }

    public List<Path> scan() {
        try (var pluginFiles = Files.newDirectoryStream(this.pluginFolder, this.pluginFileFilter)) {
            return ImmutableList.copyOf(pluginFiles);
        } catch (IOException e) {
            LOGGER.error("Something went wrong attempting to scan the plugin directory.", e);
            return ImmutableList.of();
        }
    }

    private static class PluginFileFilter implements DirectoryStream.Filter<Path> {
        @Override
        public boolean accept(Path path) throws IOException {
            if (Files.isDirectory(path)) {
                return false;
            }

            return JAR_MATCHER.matches(path);
        }
    }
}
