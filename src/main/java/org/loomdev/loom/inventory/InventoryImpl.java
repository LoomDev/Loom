package org.loomdev.loom.inventory;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.inventory.SimpleInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.inventory.Inventory;
import org.loomdev.api.item.ItemStack;
import org.loomdev.loom.item.ItemStackImpl;

import java.util.Optional;

public class InventoryImpl implements Inventory {

    private final net.minecraft.inventory.Inventory mcInventory;

    public InventoryImpl(@NotNull net.minecraft.inventory.Inventory mcInventory) {
        this.mcInventory = mcInventory;
    }

    public net.minecraft.inventory.Inventory getMinecraftInventory() {
        return mcInventory;
    }

    @Override
    public @NotNull Component getTitle() {
        return TextComponent.of("");
    }

    @Override
    public void setTitle(@NotNull String s) {

    }

    @Override
    public void setTitle(@NotNull Component component) {

    }

    @Override
    public int getSize() {
        return mcInventory.size();
    }

    @Override
    public int getMaxStack() {
        return mcInventory.getMaxCountPerStack();
    }

    @Override
    public void setMaxStack() {
        // TODO nms modification
    }

    @Override
    public @NotNull Optional<ItemStack> getItem(int slot) { // TODO probably don't need this as optional
        return Optional.of(new ItemStackImpl(mcInventory.getStack(slot)));
    }

    @Override
    public void setItem(int slot, @Nullable ItemStack itemStack) {
        mcInventory.setStack(slot, ((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public ItemStack next() {
        return null;
    }

    public static class Builder implements Inventory.Builder {

        private Inventory inventory;

        public Builder() {
            inventory = new InventoryImpl(new SimpleInventory());
        }

        @Override
        public Inventory.Builder title(@NotNull String s) {
            return this;
        }

        @Override
        public Inventory.Builder title(@NotNull Component component) {
            return null;
        }

        @Override
        public Inventory.Builder size(int i) {
            return null;
        }

        @Override
        public Inventory.Builder slot(int slot, @NotNull ItemStack itemStack) {
            inventory.setItem(slot, itemStack);
            return this;
        }

        @Override
        public Inventory.Builder from(Inventory inventory) {
            this.inventory = inventory;
            return this;
        }

        @Override
        public Inventory build() {
            return inventory;
        }
    }
}
