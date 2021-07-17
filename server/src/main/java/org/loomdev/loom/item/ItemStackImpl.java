package org.loomdev.loom.item;

import net.kyori.adventure.text.Component;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.item.Enchantment;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.ItemPropertyData;
import org.loomdev.loom.transformer.Transformer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ItemStackImpl implements ItemStack {

    private final net.minecraft.world.item.ItemStack mcStack;

    private ItemStackImpl(net.minecraft.world.item.ItemStack mcStack) {
        this.mcStack = mcStack;
    }

    @NotNull
    public net.minecraft.world.item.ItemStack getMinecraftItemStack() {
        return mcStack;
    }

    @Override
    @NotNull
    public ItemType getType() {
        var type = org.loomdev.api.util.registry.Registry.get().getWrapped(ItemType.class, Registry.ITEM.getKey(mcStack.getItem()).toString());
        if (type == null) return ItemType.AIR;
        return type;
    }

    @Override
    public void setType(@NotNull ItemType item) {
        mcStack.item = Registry.ITEM.get(Transformer.NAMESPACED_KEY.toMinecraft(item.getKey()));
        mcStack.updateEmptyCacheFlag();
    }

    @Override
    public int getAmount() {
        return mcStack.getCount();
    }

    @Override
    public void setAmount(int amount) {
        mcStack.setCount(amount);
    }

    @Override
    public void increment(int amount) {
        mcStack.grow(amount);
    }

    @Override
    public void decrement(int amount) {
        mcStack.shrink(amount);
    }

    @Override
    @NotNull
    public ItemStack split(int stackAmount) {
        return ItemStackImpl.of(mcStack.split(stackAmount));
    }

    @Override
    public <T extends ItemPropertyData<T>> T getProperty(ItemProperty<T> itemProperty) {
        return itemProperty.get(this);
    }

    @Override
    public <T extends ItemPropertyData<T>> void setProperty(@NotNull ItemProperty<T> itemProperty, @NotNull T data) {
        itemProperty.apply(this, data);
        // TODO check if nbt tag is empty, if so remove it?
    }

    @Override
    public <T extends ItemPropertyData<T>> void changeProperty(@NotNull ItemProperty<T> itemProperty, @NotNull Consumer<T> consumer) {
        T data = this.getProperty(itemProperty);
        consumer.accept(data);
        this.setProperty(itemProperty, data);
    }

    @Override
    public boolean isEdible() {
        return mcStack.isEdible();
    }

    @Override
    public boolean isStackable() {
        return mcStack.isStackable();
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
    public boolean isEmpty() {
        return mcStack.isEmpty();
    }

    @Override
    public @NotNull Component getHoverText() {
        return this.getProperty(ItemProperty.Name).getHoverText();
    }

    // endregion ItemProperties

    public static class BuilderImpl implements ItemStack.Builder {

        private ItemStack itemStack;

        public BuilderImpl() {
            this.itemStack = new ItemStackImpl(new net.minecraft.world.item.ItemStack(Items.AIR));
        }

        @Override
        public ItemStack.Builder type(@NotNull ItemType item) {
            itemStack.setType(item);
            return this;
        }

        @Override
        public ItemStack.Builder amount(int amount) {
            itemStack.setAmount(amount);
            return this;
        }

        @Override
        public ItemStack.Builder name(@NotNull String name) {
            itemStack.setName(name);
            return this;
        }

        @Override
        public ItemStack.Builder name(@NotNull Component component) {
            itemStack.setName(component);
            return this;
        }

        @Override
        public ItemStack.Builder lore(@NotNull Component... textComponents) {
            itemStack.setLore(Arrays.asList(textComponents));
            return this;
        }

        @Override
        public ItemStack.Builder appendLore(@NotNull String... lore) {
            List<Component> components = Arrays.stream(lore)
                    .map(Component::text)
                    .collect(Collectors.toList());
            itemStack.setLore(components);
            return this;
        }

        @Override
        public ItemStack.Builder appendLore(@NotNull Component... textComponents) {
            itemStack.appendLore(textComponents);
            return this;
        }

        @Override
        public ItemStack.Builder removeLoreLine(int index) {
            itemStack.removeLoreLine(index);
            return this;
        }

        @Override
        public ItemStack.Builder enchant(@NotNull Enchantment enchantment, int level) {
            itemStack.addEnchantment(enchantment, level);
            return this;
        }

        @Override
        public <T extends ItemPropertyData<T>> ItemStack.Builder property(ItemProperty<T> itemProperty, Consumer<T> consumer) {
            itemStack.changeProperty(itemProperty, consumer);
            return this;
        }

        @Override
        public ItemStack.Builder from(ItemStack itemStack) {
            this.itemStack = itemStack;
            return this;
        }

        @Override
        public ItemStack build() {
            return itemStack;
        }
    }

    @NotNull
    public static ItemStack of(@Nullable net.minecraft.world.item.ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        return new ItemStackImpl(stack);
    }
}
