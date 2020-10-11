package org.loomdev.api.config;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Configuration extends ImmutableConfiguration {

    @NotNull Configuration getRoot();

    @Nullable Configuration getParent();

    @NotNull String getPath();

    @NotNull String getFullPath();

    default String createPath(@NotNull Configuration configuration, @Nullable String key) {
        return createPath(configuration, key, configuration.getRoot());
    }

    String createPath(@NotNull Configuration configuration, @Nullable String key, @NotNull Configuration relativeTo);

    @Nullable Configuration section(@NotNull String path);

    @NotNull Configuration createSection(@NotNull String path);

    void add(@NotNull String key, @Nullable Object value);

    void set(@NotNull String key, @Nullable Object value);

    void remove(@NotNull String key);
}
