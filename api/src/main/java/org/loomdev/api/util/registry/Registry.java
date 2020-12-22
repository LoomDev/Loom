package org.loomdev.api.util.registry;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.ItemPropertyData;
import org.loomdev.api.tag.Tag;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.util.builder.BuilderBase;

import java.util.Optional;
import java.util.function.Supplier;

public interface Registry {

    <T extends ItemPropertyData<T>> void registerItemProperty(@NotNull Class<T> dataType, @NotNull Supplier<ItemProperty<T>> supplier);

    @Nullable
    <T extends ItemPropertyData<T>> ItemProperty<T> getItemProperty(@NotNull Class<T> dataType);

    @Nullable
    <V, B extends BuilderBase<V, B>> B createBuilder(@NotNull Class<V> type);

    @NotNull
    <T extends Tag<? extends Keyed>> Optional<T> getTag(@NotNull Class<? extends Keyed> type, @NotNull NamespacedKey key);

    @Nullable
    <T extends Keyed> T getWrapped(@NotNull Class<T> type, @NotNull String key);
}
