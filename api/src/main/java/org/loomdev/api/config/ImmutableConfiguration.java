package org.loomdev.api.config;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ImmutableConfiguration {

    @NotNull Set<String> getKeys();

    @NotNull Set<String> getKeys(boolean recursive);

    boolean hasKey(@NotNull String key);

    @Nullable Object get(@NotNull String key);

    @Nullable Object get(@NotNull String key, @Nullable Object defaultValue);

    @Nullable <T> T get(@NotNull Class<T> cls, @NotNull String key);

    @Nullable <T> T get(@NotNull Class<T> cls, @NotNull String key, @Nullable T defaultValue);

    default @NotNull String getString(@NotNull String key) {
        return getString(key, "");
    }

    @NotNull String getString(@NotNull String key, @NotNull String defaultValue);

    default int getInt(@NotNull String key) {
        return getInt(key, 0);
    }

    int getInt(@NotNull String key, int defaultValue);

    default double getDouble(@NotNull String key) {
        return getDouble(key, 0);
    }

    double getDouble(@NotNull String key, double defaultValue);

    default float getFloat(@NotNull String key) {
        return getFloat(key, 0f);
    }

    float getFloat(@NotNull String key, float defaultValue);

    default byte getByte(@NotNull String key) {
        return getByte(key, (byte) 0);
    }

    byte getByte(@NotNull String key, byte defaultValue);

    default long getLong(@NotNull String key) {
        return getLong(key, 0L);
    }

    long getLong(@NotNull String key, long defaultValue);

    default short getShort(@NotNull String key) {
        return getShort(key, (short)0);
    }

    short getShort(@NotNull String key, short defaultValue);

    default boolean getBoolean(@NotNull String key) {
        return getBoolean(key, false);
    }

    boolean getBoolean(@NotNull String key, boolean defaultValue);

    @Nullable <T> Collection<T> getCollection(@NotNull Class<T> cls, @NotNull String key, @NotNull Collection<T> target);

    default @Nullable <T> List<T> getList(@NotNull Class<T> cls, @NotNull String key) {
        return getList(cls, key, new ArrayList<>());
    }

    @Nullable <T> List<T> getList(@NotNull Class<T> cls, @NotNull String key, @Nullable List<T> defaultValue);

    default @NotNull <T> T[] getArray(@NotNull Class<T> cls, @NotNull String key) {
        return getArray(cls, key, (T[]) Array.newInstance(cls, 0));
    }

    @NotNull <T> T[] getArray(@NotNull Class<T> cls, @NotNull String key, @Nullable T[] defaultValue);

    default <T extends Enum<T>> T getEnum(Class<T> enumType, @NotNull String key) {
        try {
            return Enum.valueOf(enumType, getString(key));
        } catch (IllegalArgumentException e) {
            throw new ConfigurationException(e);
        }
    }

    default <T extends Enum<T>> T getEnum(Class<T> enumType, @NotNull String key, T defaultValue) {
        String stringValue = getString(key);
        if (stringValue == null) {
            return defaultValue;
        }

        try {
            return Enum.valueOf(enumType, stringValue);
        } catch (IllegalArgumentException e) {
            throw new ConfigurationException(e);
        }
    }

}
