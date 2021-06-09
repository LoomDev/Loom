package org.loomdev.api.util.registry;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.command.CommandSyntaxExeception;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.ItemPropertyData;
import org.loomdev.api.tag.Tag;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.util.builder.BuilderBase;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class Registry {

    private static Registry registry;

    public static void setRegistry(Registry registry) {
        if (Registry.registry != null) {
            throw new UnsupportedOperationException("The registry instance cannot be modified after it has been set.");
        }
        Registry.registry = registry;
    }

    public static Registry get() {
        return registry;
    }

    public abstract <T extends ItemPropertyData<T>> void registerItemProperty(@NotNull Class<T> dataType, @NotNull Supplier<ItemProperty<T>> supplier);

    @Nullable
    public abstract <T extends ItemPropertyData<T>> ItemProperty<T> getItemProperty(@NotNull Class<T> dataType);

    @Nullable
    public abstract <V, B extends BuilderBase<V, B>> B createBuilder(@NotNull Class<V> type);

    @NotNull
    public abstract <T extends Tag<? extends Keyed>> Optional<T> getTag(@NotNull Class<? extends Keyed> type, @NotNull NamespacedKey key);

    // TODO should use NamespacedKey.
    @Nullable
    public abstract <T extends Keyed> T getWrapped(@NotNull Class<T> type, @NotNull String key);

    @NotNull
    public abstract CommandSyntaxExeception createCommandSyntaxException(String text, String input, int cursor);
}
