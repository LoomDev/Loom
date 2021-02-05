package org.loomdev.loom.plugin.metadata;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.ApiVersion;
import org.loomdev.api.plugin.metadata.PluginDependency;
import org.loomdev.api.plugin.metadata.PluginMetadata;

import java.util.Objects;
import java.util.Optional;

public class PluginMetadataImpl implements PluginMetadata {

    @NotNull
    private final String id;

    @NotNull
    private final String name;

    @NotNull
    private final String mainClass;

    @NotNull
    private final String version;

    @Nullable
    private final String description;

    @NotNull
    private final String[] authors;

    @NotNull
    private final PluginDependency[] dependencies;

    @NotNull
    private final ApiVersion minimumApiVersion;

    public PluginMetadataImpl(
            @NotNull String id,
            @NotNull String name,
            @NotNull String mainClass,
            @NotNull String version,
            @Nullable String description,
            @NotNull String[] authors,
            @NotNull PluginDependency[] dependencies,
            @NotNull ApiVersion minimumApiVersion
    ) throws NullPointerException {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(mainClass, "mainClass");
        Objects.requireNonNull(version, "version");
        Objects.requireNonNull(authors, "authors");
        Objects.requireNonNull(dependencies, "dependencies");
        Objects.requireNonNull(minimumApiVersion, "minimumApiVersion");

        this.id = id;
        this.name = name;
        this.mainClass = mainClass;
        this.version = version;
        this.description = description == null || description.isEmpty() ? null : description;
        this.authors = authors;
        this.dependencies = dependencies;
        this.minimumApiVersion = minimumApiVersion;
    }

    @NotNull
    public String getId() {
        return id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getMainClass() {
        return mainClass;
    }

    @NotNull
    public String getVersion() {
        return version;
    }

    @NotNull
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    @NotNull
    public String[] getAuthors() {
        return authors;
    }

    @NotNull
    public PluginDependency[] getDependencies() {
        return dependencies;
    }

    @NotNull
    public ApiVersion getMinimumApiVersion() {
        return minimumApiVersion;
    }
}
