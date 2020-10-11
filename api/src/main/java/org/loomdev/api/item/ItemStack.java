package org.loomdev.api.item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.ItemPropertyData;
import org.loomdev.api.util.builder.BuilderBase;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Represents an ItemStack.
 */
public interface ItemStack {

    /**
     * An empty ItemStack.
     */
    ItemStack EMPTY = ItemStack.builder().type(ItemType.AIR).build();

    static Builder builder() {
        return Loom.getRegistry().createBuilder(ItemStack.class);
    }

    /**
     * Get the item type of this ItemStack.
     *
     * @return The type of the item.
     * @see ItemType
     */
    @NotNull ItemType getType();

    /**
     * Set the item type of this ItemStack.
     *
     * @param type The item type.
     * @see ItemType
     */
    void setType(@NotNull ItemType type);

    /**
     * Get the amount of items in the ItemStack.
     *
     * @return The amount of items in the ItemStack.
     */
    int getAmount();

    /**
     * Set the amount of items in the ItemStack.
     *
     * @param amount The new amount of items.
     */
    void setAmount(int amount);

    /**
     * Increment the amount of items in the ItemStack by 1.
     */
    default void increment() {
        increment(1);
    }

    /**
     * Increment the amount of items in the ItemStack by a specific amount.
     *
     * @param amount The amount to increment the ItemStack size with.
     */
    void increment(int amount);

    /**
     * Decrement the amount of items in the ItemStack by 1.
     */
    default void decrement() {
        decrement(1);
    }

    /**
     * Decrement the amount of items in the ItemStack by a specific amount.
     *
     * @param amount The amount to decrement the ItemStack size with.
     */
    void decrement(int amount);

    /**
     * Split the ItemStack into two stacks.
     *
     * <pre>{@code
     * ItemStack firstStack = ItemStack.builder().type(ItemType.STONE).amount(45).build();
     *
     * // Split the original ItemStack in two.
     * ItemStack secondStack = firstStack.split(15);
     *
     * firstStack.getAmount(); // Amount = 30
     * secondStack.getAmount(); // Amount = 15
     * }</pre>
     *
     * @param splitCount The amount of items in the returned stack.
     * @return A the split of ItemStack.
     */
    @NotNull ItemStack split(int splitCount);

    <T extends ItemPropertyData<T>> @NotNull T getProperty(@NotNull ItemProperty<T> property);

    <T extends ItemPropertyData<T>> void setProperty(@NotNull ItemProperty<T> property, @NotNull T data);

    <T extends ItemPropertyData<T>> void changeProperty(@NotNull ItemProperty<T> property, @NotNull Consumer<T> data);

    /**
     * Get whether the itemstack is a food item.
     *
     * @return True if the item can be consumed, otherwise false.
     */
    boolean isFood();

    /**
     * Get whether the item is stackable or not.
     *
     * @return True if stackable, otherwise false.
     */
    boolean isStackable();

    // region general item properties

    /**
     * Get the current name of the {@link org.loomdev.api.item.ItemStack}.
     *
     * @return The current name.
     */
    @NotNull Component getName();

    /**
     * Change the name of the {@link org.loomdev.api.item.ItemStack}.
     *
     * @param name The new name.
     */
    default void setName(@NotNull String name) {
        setName(TextComponent.of(name));
    }

    /**
     * Change the name of the {@link org.loomdev.api.item.ItemStack}.
     *
     * @param name The new name.
     */
    void setName(@NotNull Component name);

    /**
     * Reset the name of the {@link org.loomdev.api.item.ItemStack} to the name of the item.
     */
    void removeCustomName();

    /**
     * Check whether the {@link org.loomdev.api.item.ItemStack} has a custom name.
     *
     * @return True if the names doesn't equal the default minecraft name.
     */
    boolean hasCustomName();

    /**
     * Get the chat hover text for the {@link org.loomdev.api.item.ItemStack}.
     *
     * @return The hover text.
     */
    @NotNull Component getHoverText();

    /**
     * Get the lore of the {@link org.loomdev.api.item.ItemStack}.
     *
     * @return The lore of the {@link org.loomdev.api.item.ItemStack}.
     */
    @NotNull List<Component> getLore();

    /**
     * Set the lore of the {@link org.loomdev.api.item.ItemStack}.
     *
     * @param components The new lore of the {@link org.loomdev.api.item.ItemStack}.
     */
    void setLore(@NotNull List<Component> components);

    /**
     * Append extra lore to the current lore.
     *
     * @param lore The lore to add.
     */
    default void appendLore(String... lore) {
        Arrays.stream(lore).forEach(l -> appendLore(TextComponent.of(l)));
    }

    /**
     * Append extra lore to the current lore.
     *
     * @param lore The lore to add.
     */
    void appendLore(Component... lore);

    /**
     * Remove a specific line from the lore.
     *
     * @param lineIndex The index of the line th remove.
     */
    void removeLoreLine(int lineIndex);

    /**
     * Get the enchantments of the {@link org.loomdev.api.item.ItemStack}.
     *
     * @return Map of enchantment and level.
     */
    @NotNull Map<Enchantment, Integer> getEnchantments();

    /**
     * Set the enchantments of the {@link org.loomdev.api.item.ItemStack}.
     *
     * @param enchantments The enchantments.
     */
    void setEnchantments(@NotNull Map<Enchantment, Integer> enchantments);

    /**
     * Get the level of a specific enchantment on the {@link org.loomdev.api.item.ItemStack}.
     *
     * @param enchantment The enchantment to get the level for.
     * @return The level of the enchantment. 0 if the enchantment is not applied to the {@link org.loomdev.api.item.ItemStack}.
     */
    int getEnchantmentLevel(Enchantment enchantment);

    /**
     * Add an enchantment to the {@link org.loomdev.api.item.ItemStack}.
     *
     * @param enchantment The enchantment to add.
     * @param level The level of the enchantment.
     */
    void addEnchantment(@NotNull Enchantment enchantment, Integer level);

    /**
     * Remove an enchantment of the {@link org.loomdev.api.item.ItemStack}.
     *
     * @param enchantment The enchantment to remove.
     */
    void removeEnchantment(@NotNull Enchantment enchantment);

    /**
     * Remove all enchantments of the {@link org.loomdev.api.item.ItemStack}.
     */
    void clearEnchantments();

    /**
     * Check whether the {@link org.loomdev.api.item.ItemStack} has a specific enchantment.
     *
     * @param enchantment The enchantment to check for.
     * @return True if the enchantment is applied to the {@link org.loomdev.api.item.ItemStack}.
     */
    boolean hasEnchantment(@NotNull Enchantment enchantment);

    // endregion general item properties

    /**
     * Builder for ItemStacks
     */
    interface Builder extends BuilderBase<ItemStack, Builder> {

        Builder type(@NotNull ItemType type);

        Builder amount(int amount);

        Builder name(@NotNull String name);

        Builder name(@NotNull Component name);

        default Builder lore(@NotNull String... lore) {
            return lore((TextComponent[]) Arrays.stream(lore).map(TextComponent::of).toArray());
        }

        Builder lore(@NotNull TextComponent... lore);

        Builder appendLore(@NotNull String... lore);

        Builder appendLore(@NotNull TextComponent... lore);

        Builder removeLoreLine(int line);

        Builder enchant(@NotNull Enchantment enchantment, int level);

        <T extends ItemPropertyData<T>> Builder property(@NotNull ItemProperty<T> property, @NotNull Consumer<T> data);

    }
}
