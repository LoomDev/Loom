package org.loomdev.ap.plugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SerializablePluginMetadata {

    @NotNull
    public String mainClass;

    @NotNull
    public String id;

    @NotNull
    public String name;

    @NotNull
    public String version;

    @Nullable
    public String description;

    @NotNull
    public String[] authors;

    @NotNull
    public Dependency[] dependencies;

    @NotNull
    public String minimumApiVersion;

    /**
     * Used for deserialization (keep private)
     */
    private SerializablePluginMetadata() { }

    public SerializablePluginMetadata(
            @NotNull String mainClass,
            @NotNull String id,
            @NotNull String name,
            @NotNull String version,
            @Nullable String description,
            @NotNull String[] authors,
            @NotNull Dependency[] dependencies,
            @NotNull String minimumApiVersion
    ) {
        this.mainClass = mainClass;
        this.id = id;
        this.name = name;
        this.version = version;
        this.description = description;
        this.authors = authors;
        this.dependencies = dependencies;
        this.minimumApiVersion = minimumApiVersion;
    }

    public static final class Dependency {

        @NotNull
        public String id;

        @Nullable
        public String version;

        public boolean required;

        /**
         * Used for deserialization (keep private)
         */
        private Dependency() {
        }

        public Dependency(@NotNull String id, @Nullable String version, boolean required) {
            this.id = id;
            this.version = version;
            this.required = required;
        }
    }

}
