package org.loomdev.loom.plugin.loader.java;

import org.loomdev.loom.plugin.loader.LoomPluginMetadata;

import java.nio.file.Path;
import java.util.List;

public class JavaLoomPluginMetadata extends LoomPluginMetadata {
    private final Class<?> mainClass;

    public JavaLoomPluginMetadata(String id, String name, String version, String description, List<String> authors, Path source, Class<?> mainClass) {
        super(id, name, version, description, authors, source);
        this.mainClass = mainClass;
    }

    Class<?> getMainClass() {
        return mainClass;
    }
}
