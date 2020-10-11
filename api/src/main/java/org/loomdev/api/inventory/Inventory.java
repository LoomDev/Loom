package org.loomdev.api.inventory;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.util.builder.BuilderBase;

import java.util.Iterator;
import java.util.Optional;

public interface Inventory extends Iterator<ItemStack> {

    static Builder builder() {
        return Loom.getRegistry().createBuilder(Inventory.class);
    }

    @NotNull Component getTitle();

    void setTitle(@NotNull String title);

    void setTitle(@NotNull Component title);

    int getSize();

    int getMaxStack();

    void setMaxStack();

    @NotNull Optional<ItemStack> getItem(int slot);

    void setItem(int slot, @Nullable ItemStack item);

    interface Builder extends BuilderBase<Inventory, Builder> {

        Builder title(@NotNull String title);

        Builder title(@NotNull Component title);

        Builder size(int size);

        // TODO holder, type, size

        Builder slot(int slot, @NotNull ItemStack item);
    }
}
