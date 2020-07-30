package org.loomdev.loom.plugin.data;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.ApiVersion;
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
    private final List<PluginDependency> dependencies;
    private final ApiVersion apiVersion;
    private final Path source;
    private final String main;
    private State state;

    public LoomPluginMetadata(String id, String name, String version, String description, List<String> authors, List<PluginDependency> dependencies, ApiVersion apiVersion, @NotNull Path source, @NotNull String main) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.description = description;
        this.authors = authors;
        this.dependencies = dependencies;
        this.apiVersion = apiVersion;
        this.source = source;
        this.main = main;
        this.state = State.DISABLED;
    }

    @Override
    public @NotNull String getId() {
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
    public List<PluginDependency> getDependencies() {
        return this.dependencies == null || this.dependencies.size() == 0 ? ImmutableList.of() : this.dependencies;
    }

    @Override
    public @NotNull ApiVersion getMinimumApiVersion() {
        return this.apiVersion;
    }

    @Override
    public @NotNull Path getSource() {
        return this.source;
    }

    @Override
    public @NotNull String getMain() {
        return this.main;
    }

    @Override
    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
