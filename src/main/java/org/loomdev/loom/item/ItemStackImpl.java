package org.loomdev.loom.item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.item.Enchantment;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.ItemPropertyData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ItemStackImpl implements ItemStack {

    private final net.minecraft.item.ItemStack mcStack;

    private ItemStackImpl(net.minecraft.item.ItemStack mcStack) {
        this.mcStack = mcStack;
    }

    public @NotNull net.minecraft.item.ItemStack getMinecraftItemStack() {
        return this.mcStack;
    }

    @Override
    public @NotNull ItemType getType() {
        Identifier identifier = Registry.ITEM.getId(this.mcStack.getItem());
        return identifier == null ? ItemType.AIR : Loom.getRegistry().getWrapped(ItemType.class, identifier.toString());
    }

    @Override
    public void setType(@NotNull ItemType item) {
        this.mcStack.item = Registry.ITEM.get(new Identifier(item.getKey().toString()));
        this.mcStack.updateEmptyState();
    }

    @Override
    public int getAmount() {
        return this.mcStack.getCount();
    }

    @Override
    public void setAmount(int amount) {
        this.mcStack.setCount(amount);
    }

    @Override
    public void increment(int amount) {
        this.mcStack.increment(amount);
    }

    @Override
    public void decrement(int amount) {
        this.mcStack.decrement(amount);
    }

    @Override
    public @NotNull ItemStack split(int newStackAmount) {
        return ItemStackImpl.ofMcStack(this.mcStack.split(newStackAmount));
    }

    @Override
    public <T extends ItemPropertyData<T>> T getProperty(ItemProperty<T> itemProperty) {
        return itemProperty.get(this);
    }

    @Override
    public <T extends ItemPropertyData<T>> void setProperty(@NotNull ItemProperty<T> itemProperty, @NotNull T data) {
        itemProperty.apply(this, data);
    }

    @Override
    public <T extends ItemPropertyData<T>> void changeProperty(@NotNull ItemProperty<T> itemProperty, @NotNull Consumer<T> consumer) {
        T data = this.getProperty(itemProperty);
        consumer.accept(data);
        this.setProperty(itemProperty, data);
    }

    @Override
    public boolean isFood() {
        return this.mcStack.isFood();
    }

    @Override
    public boolean isStackable() {
        return this.mcStack.isStackable();
    }

    // region ItemProperties

    @Override
    public @NotNull Map<Enchantment, Integer> getEnchantments() {
        return this.getProperty(ItemProperty.Enchantments).getEnchantments();
    }

    @Override
    public void setEnchantments(@NotNull Map<Enchantment, Integer> map) {
        this.changeProperty(ItemProperty.Enchantments, data -> data.setEnchantments(map));
    }

    @Override
    public int getEnchantmentLevel(Enchantment enchantment) {
        return this.getProperty(ItemProperty.Enchantments).getEnchantmentLevel(enchantment);
    }

    @Override
    public void addEnchantment(@NotNull Enchantment enchantment, Integer level) {
        this.changeProperty(ItemProperty.Enchantments, data -> data.addEnchantment(enchantment, level));
    }

    @Override
    public void removeEnchantment(@NotNull Enchantment enchantment) {
        this.changeProperty(ItemProperty.Enchantments, data -> data.removeEnchantment(enchantment));
    }

    @Override
    public void clearEnchantments() {
        this.changeProperty(ItemProperty.Enchantments, data -> data.clearEnchantments());
    }

    @Override
    public boolean hasEnchantment(@NotNull Enchantment enchantment) {
        return this.getProperty(ItemProperty.Enchantments).hasEnchantment(enchantment);
    }

    @Override
    public @NotNull List<Component> getLore() {
        return this.getProperty(ItemProperty.Lore).getLore();
    }

    @Override
    public void setLore(@NotNull List<Component> list) {
        this.changeProperty(ItemProperty.Lore, data -> data.setLore(list));
    }

    @Override
    public void appendLore(Component... components) {
        this.changeProperty(ItemProperty.Lore, data -> data.appendLore(components));
    }

    @Override
    public void removeLoreLine(int index) {
        this.changeProperty(ItemProperty.Lore, data -> data.removeLoreLine(index));
    }

    @Override
    public @NotNull Component getName() {
        return this.getProperty(ItemProperty.Name).getName();
    }

    @Override
    public void setName(@NotNull Component component) {
        this.changeProperty(ItemProperty.Name, data -> data.setName(component));
    }

    @Override
    public void removeCustomName() {
        this.changeProperty(ItemProperty.Name, data -> data.removeCustomName());
    }

    @Override
    public boolean hasCustomName() {
        return this.getProperty(ItemProperty.Name).hasCustomName();
    }

    @Override
    public @NotNull Component getHoverText() {
        return this.getProperty(ItemProperty.Name).getHoverText();
    }

    // endregion ItemProperties

    public static class BuilderImpl implements ItemStack.Builder {

        private ItemStack itemStack;

        public BuilderImpl() {
            this.itemStack = new ItemStackImpl(new net.minecraft.item.ItemStack(Items.AIR));
        }

        @Override
        public ItemStack.Builder type(@NotNull ItemType item) {
            this.itemStack.setType(item);
            return this;
        }

        @Override
        public ItemStack.Builder amount(int amount) {
            this.itemStack.setAmount(amount);
            return this;
        }

        @Override
        public ItemStack.Builder name(@NotNull String name) {
            this.itemStack.setName(name);
            return this;
        }

        @Override
        public ItemStack.Builder name(@NotNull Component component) {
            this.itemStack.setName(component);
            return this;
        }

        @Override
        public ItemStack.Builder lore(@NotNull TextComponent... textComponents) {
            this.itemStack.setLore(Arrays.asList(textComponents));
            return this;
        }

        @Override
        public ItemStack.Builder appendLore(@NotNull String... lore) {
            List<Component> components = Arrays.stream(lore)
                    .map(TextComponent::of)
                    .collect(Collectors.toList());
            this.itemStack.setLore(components);
            return this;
        }

        @Override
        public ItemStack.Builder appendLore(@NotNull TextComponent... textComponents) {
            this.itemStack.appendLore(textComponents);
            return this;
        }

        @Override
        public ItemStack.Builder removeLoreLine(int index) {
            this.itemStack.removeLoreLine(index);
            return this;
        }

        @Override
        public ItemStack.Builder enchant(@NotNull Enchantment enchantment, int level) {
            this.itemStack.addEnchantment(enchantment, level);
            return this;
        }

        @Override
        public <T extends ItemPropertyData<T>> ItemStack.Builder property(ItemProperty<T> itemProperty, Consumer<T> consumer) {
            this.itemStack.changeProperty(itemProperty, consumer);
            return this;
        }

        @Override
        public ItemStack.Builder from(ItemStack itemStack) {
            this.itemStack = itemStack;
            return this;
        }

        @Override
        public ItemStack build() {
            return this.itemStack;
        }
    }

    public static ItemStack ofMcStack(net.minecraft.item.ItemStack stack) {
        if(stack == net.minecraft.item.ItemStack.EMPTY) {
            return ItemStack.EMPTY;
        }
        return new ItemStackImpl(stack);
    }
}
