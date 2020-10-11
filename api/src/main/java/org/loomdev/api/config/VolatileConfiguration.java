package org.loomdev.api.config;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class VolatileConfiguration implements Configuration {

    protected static String pathSeparator = ".";
    protected final Map<String, Object> data = new LinkedHashMap<>();
    private final Configuration root;
    private final Configuration parent;
    private final String path;
    private final String fullPath;

    protected VolatileConfiguration() {
        this.path = "";
        this.fullPath = "";
        this.root = this;
        this.parent = null;
    }

    protected VolatileConfiguration(@NotNull Configuration parent, @NotNull String path) {
        this.path = path;
        this.fullPath = parent.getFullPath() + pathSeparator + path;
        this.root = parent.getRoot();
        this.parent = parent;
    }

    @Override
    public @NotNull Configuration getRoot() {
        return root;
    }

    @Override
    public Configuration getParent() {
        return parent;
    }

    @Override
    public @NotNull String getPath() {
        return path;
    }

    @Override
    public @NotNull String getFullPath() {
        return fullPath;
    }

    @Override
    public String createPath(@NotNull Configuration configuration, @Nullable String key, @NotNull Configuration relativeTo) {
        Configuration root = configuration.getRoot();
        if (root == null) {
            throw new IllegalStateException("Cannot create path without a root");
        }

        StringBuilder builder = new StringBuilder();
        for (Configuration parent = configuration; (parent != null) && (parent != relativeTo); parent = parent.getParent()) {
            if (builder.length() > 0) {
                builder.insert(0, pathSeparator);
            }
            builder.insert(0, parent.getPath());
        }

        if ((key != null) && (key.length() > 0)) {
            if (builder.length() > 0) {
                builder.append(pathSeparator);
            }
            builder.append(key);
        }

        return builder.toString();
    }

    @Override
    public Configuration section(@NotNull String path) {
        Object val = get(path, null);
        return (val instanceof Configuration) ? (Configuration) val : null;
    }

    @Override
    public @NotNull Configuration createSection(@NotNull String path) {
        Configuration root = getRoot();
        if (root == null) {
            throw new IllegalStateException("Cannot create subset without a root");
        }

        String[] keys = safeKeySplit(path, pathSeparator);
        if (keys.length == 0)
            keys = new String[] { path };

        Configuration subset = this.section(keys[0]);
        if (keys.length == 1) {
            if (subset != null) {
                return subset;
            }
            Configuration sub = new VolatileConfiguration(this, keys[0]);
            this.set(keys[0], sub);
            return sub;
        } else {
            String nextPath = path.substring(path.indexOf(pathSeparator));

            if (subset != null) {
                return subset.createSection(nextPath);
            }
            Configuration sub = new VolatileConfiguration(this, keys[0]);
            this.set(keys[0], sub);
            return sub.createSection(nextPath);
        }
    }

    @Override
    public void add(@NotNull String key, Object value) {
        if (!key.contains(pathSeparator)) {
            // TODO should we throw an exception if someone tries to add a key that already exists. (use set instead?)
            this.data.put(key, value);
            return;
        }

        String subsetKey = safeKeySplit(key, pathSeparator)[0];
        Configuration subset = section(subsetKey);
        if (subset == null) {
            subset = this.createSection(subsetKey);
        }

        String keyRemainder = key.substring(key.indexOf(pathSeparator) + 1);
        subset.set(keyRemainder, value);
    }

    @Override
    public void set(@NotNull String key, Object value) {
        if (!key.contains(pathSeparator)) {
            this.data.put(key, value);
            return;
        }

        String subsetKey = safeKeySplit(key, pathSeparator)[0];
        Configuration subset = section(subsetKey);
        if (subset == null) {
            subset = this.createSection(subsetKey);
        }

        String keyRemainder = key.substring(key.indexOf(pathSeparator) + 1);
        subset.set(keyRemainder, value);
    }

    @Override
    public void remove(@NotNull String key) {
        if (!key.contains(pathSeparator)) {
            this.data.remove(key);
            return;
        }

        String subsetKey = safeKeySplit(key, pathSeparator)[0];
        Configuration subset = section(subsetKey);
        if (subset == null) {
            return;
        }

        String keyRemainder = key.substring(key.indexOf(pathSeparator) + 1);
        subset.remove(keyRemainder);
    }

    @Override
    public @NotNull Set<String> getKeys() {
        return getKeys(false);
    }

    @Override
    public @NotNull Set<String> getKeys(boolean recursive) {
        Set<String> result = new LinkedHashSet<>(data.keySet());
        if (recursive) {
            for (String key : data.keySet()) {
                Configuration configuration = section(key);
                if (configuration != null) {
                    result.addAll(configuration.getKeys(true).stream()
                            .map(k -> key + pathSeparator + k)
                            .collect(Collectors.toList()));
                }
            }
        }
        return result;
    }

    @Override
    public boolean hasKey(@NotNull String key) {
        if (!key.contains(pathSeparator)) {
            return this.data.containsKey(key);
        }

        String subsetKey = safeKeySplit(key, pathSeparator)[0];
        Configuration subset = section(subsetKey);
        if (subset == null) {
            return false;
        }

        String keyRemainder = key.substring(key.indexOf(pathSeparator) + 1);
        return subset.hasKey(keyRemainder);
    }

    @Override
    public Object get(@NotNull String key) {
        return this.get(key, null);
    }

    @Override
    public Object get(@NotNull String key, Object defaultValue) {
        if (!key.contains(pathSeparator)) {
            return this.data.getOrDefault(key, defaultValue);
        }

        String subsetKey = safeKeySplit(key, pathSeparator)[0];
        Configuration subset = section(subsetKey);
        if (subset == null) {
            return null;
        }

        String keyRemainder = key.substring(key.indexOf(pathSeparator) + 1);
        return subset.get(keyRemainder, defaultValue);
    }

    @Override
    public <T> T get(@NotNull Class<T> cls, @NotNull String key) {
        return (T) this.get(key);
    }

    @Override
    public <T> T get(@NotNull Class<T> cls, @NotNull String key, T defaultValue) {
        return (T) this.get(key, defaultValue);
    }

    @Override
    public @NotNull String getString(@NotNull String key, @NotNull String defaultValue) {
        return get(String.class, key, defaultValue);
    }

    @Override
    public int getInt(@NotNull String key, int defaultValue) {
        return get(int.class, key, defaultValue);
    }

    @Override
    public double getDouble(@NotNull String key, double defaultValue) {
        return get(double.class, key, defaultValue);
    }

    @Override
    public float getFloat(@NotNull String key, float defaultValue) {
        return get(float.class, key, defaultValue);
    }

    @Override
    public byte getByte(@NotNull String key, byte defaultValue) {
        return get(byte.class, key, defaultValue);
    }

    @Override
    public long getLong(@NotNull String key, long defaultValue) {
        return get(long.class, key, defaultValue);
    }

    @Override
    public short getShort(@NotNull String key, short defaultValue) {
        return get(short.class, key, defaultValue);
    }

    @Override
    public boolean getBoolean(@NotNull String key, boolean defaultValue) {
        return get(boolean.class, key, defaultValue);
    }

    @Override
    public <T> Collection<T> getCollection(@NotNull Class<T> cls, @NotNull String key, @NotNull Collection<T> target) {
        target.addAll((Collection<T>) get(cls, key));
        return target;
    }

    @Override
    public <T> List<T> getList(@NotNull Class<T> cls, @NotNull String key, List<T> defaultValue) {
        return (List<T>) get(List.class, key, defaultValue);
    }

    @Override
    public <T> @NotNull T[] getArray(@NotNull Class<T> cls, @NotNull String key, @Nullable T[] defaultValue) {
        Object value = get(key, defaultValue);
        if (value instanceof List) {
            return (T[]) ((List) value).toArray();
        } else if (value.getClass().isArray()) {
            return (T[]) get(key, defaultValue);
        }
        throw new ConfigurationException(String.format("An error has occurred while attempting to cast '%s' to an array of type '%s'", value.getClass().getCanonicalName(), cls.getCanonicalName()));
    }

    private String[] safeKeySplit(String input, String delimiter) {
        return input.split(delimiter.equals(".") ? "\\." : delimiter);
    }
}
