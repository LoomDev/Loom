package org.loomdev.loom.plugin.loader;

import com.google.common.collect.ImmutableList;
import org.loomdev.api.plugin.PluginMetadata;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class LoomPluginMetadata implements PluginMetadata {

    private final String id;
    private final String name;
    private final String version;
    private final String description;
    private final List<String> authors;
    private final Path source;

    public LoomPluginMetadata(String id, String name, String version, String description, List<String> authors, Path source) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.description = description;
        this.authors = authors;
        this.source = source;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Optional<String> getName() {
        return Optional.ofNullable(this.name);
    }

    @Override
    public Optional<String> getVersion() {
        return Optional.ofNullable(this.version);
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.ofNullable(this.description);
    }

    @Override
    public List<String> getAuthors() {
        return this.authors == null || this.authors.size() == 0 ? ImmutableList.of() : this.authors;
    }

    @Override
    public Optional<Path> getSource() {
        return Optional.ofNullable(this.source);
    }
}
