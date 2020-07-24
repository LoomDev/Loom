package org.loomdev.loom.item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.item.Enchantment;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemTypes;
import org.loomdev.api.item.property.ItemProperties;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.loom.item.property.data.LoreDataImpl;
import org.loomdev.loom.util.transformer.TextTransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ItemStackImpl implements ItemStack {

    private final net.minecraft.item.ItemStack mcStack;

    public ItemStackImpl(net.minecraft.item.ItemStack mcStack) {
        this.mcStack = mcStack;
    }

    public net.minecraft.item.ItemStack getMinecraftItemStack() {
        return this.mcStack;
    }

    @Override
    public @NotNull ItemTypes getType() {
        Identifier identifier = Registry.ITEM.getId(this.mcStack.getItem());
        return identifier == null ? ItemTypes.AIR : ItemTypes.getById(identifier.toString());
    }

    @Override
    public void setType(@NotNull ItemTypes itemTypes) {
        this.mcStack.item = Registry.ITEM.get(new Identifier(itemTypes.getId()));
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
    public @NotNull Component getName() {
        return TextTransformer.toLoom(this.mcStack.getName());
    }

    @Override
    public void setName(@NotNull Component component) {
        this.mcStack.setCustomName(TextTransformer.toMinecraft(component));
    }

    @Override
    public void removeCustomName() {
        this.mcStack.removeCustomName();
    }

    @Override
    public @NotNull List<Component> getLore() {
        return getProperty(ItemProperties.Lore).getLore();
    }

    @Override
    public void setLore(@NotNull String... lore) {
        List<Component> components = new ArrayList<>();
        for (String loreLine : lore) {
            components.add(TextComponent.of(loreLine));
        }
        setProperty(ItemProperties.Lore, new LoreDataImpl(components));
    }

    @Override
    public void setLore(@NotNull List<Component> lore) {
        setProperty(ItemProperties.Lore, new LoreDataImpl(lore));
    }

    @Override
    public <T> T getProperty(ItemProperty<T> itemProperty) {
        return itemProperty.get(this);
    }

    @Override
    public <T> void setProperty(ItemProperty<T> itemProperty, T data) {
        itemProperty.apply(this, data);
    }

    @Override
    public Component getHoverText() {
        return TextTransformer.toLoom(this.mcStack.toHoverableText());
    }

    @Override
    public boolean isFood() {
        return this.mcStack.isFood();
    }

    @Override
    public boolean isStackable() {
        return this.mcStack.isStackable();
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
    public ItemStack spit(int newStackAmount) {
        return new ItemStackImpl(this.mcStack.split(newStackAmount));
    }

    public static class BuilderImpl implements ItemStack.Builder {
        ItemStack itemStack;

        public BuilderImpl() {
            this.itemStack = new ItemStackImpl(new net.minecraft.item.ItemStack(Items.AIR));
        }

        @Override
        public ItemStack.Builder type(@NotNull ItemTypes itemTypes) {
            this.itemStack.setType(itemTypes);
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
            this.itemStack.setLore(lore);
            return this;
        }

        @Override
        public ItemStack.Builder appendLore(@NotNull TextComponent... textComponents) {
            List<Component> lore = this.itemStack.getLore();
            lore.addAll(Arrays.asList(textComponents));
            this.itemStack.setLore(lore);
            return this;
        }

        @Override
        public ItemStack.Builder removeLoreLine(int index) {
            List<Component> lore = this.itemStack.getLore();
            if (index >= 0 && index < lore.size()) {
                lore.remove(index);
                this.itemStack.setLore(lore);
            }
            return this;
        }

        @Override
        public ItemStack.Builder enchant(Enchantment enchantment, int i) {
            return null;
        }

        @Override
        public <T> ItemStack.Builder property(ItemProperty<T> itemProperty, Consumer<T> consumer) {
            T data = this.itemStack.getProperty(itemProperty);
            consumer.accept(data);
            this.itemStack.setProperty(itemProperty, data);
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
}
