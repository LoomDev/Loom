package org.loomdev.api.item;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.item.property.data.ItemPropertyData;
import org.loomdev.api.util.builder.BuilderBase;
import org.loomdev.api.util.registry.Registry;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Represents an item stack.
 */
public interface ItemStack {

    /**
     * An empty item stack.
     */
    ItemStack EMPTY = ItemStack.builder().type(ItemType.AIR).build();

    static Builder builder() {
        return Registry.get().createBuilder(ItemStack.class);
    }

    static Builder builder(ItemType type) {
        return builder().type(type);
    }

    static Builder builder(ItemType type, int amount) {
        return builder().type(type).amount(amount);
    }

    /**
     * Gets the item type of this item stack.
     *
     * @return The type of the item.
     * @see ItemType
     */
    @NotNull ItemType getType();

    /**
     * Sets the item type of this item stack.
     *
     * @param type The item type.
     * @see ItemType
     */
    void setType(@NotNull ItemType type);

    /**
     * Gets the amount of items in the item stack.
     *
     * @return The amount of items in the item stack.
     */
    int getAmount();

    /**
     * Sets the amount of items in the item stack.
     *
     * @param amount The new amount of items.
     */
    void setAmount(int amount);

    /**
     * Increments the amount of items in the item stack by 1.
     */
    default void increment() {
        increment(1);
    }

    /**
     * Increments the amount of items in the item stack by a specific amount.
     *
     * @param amount The amount to increment the item stack size with.
     */
    void increment(int amount);

    /**
     * Decrements the amount of items in the item stack by 1.
     */
    default void decrement() {
        decrement(1);
    }

    /**
     * Decrements the amount of items in the item stack by a specific amount.
     *
     * @param amount The amount to decrement the item stack size with.
     */
    void decrement(int amount);

    /**
     * Splits the item stack into two stacks.
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
     * @return The split item stack.
     */
    @NotNull ItemStack split(int splitCount);

    <T extends ItemPropertyData<T>> @NotNull T getProperty(@NotNull ItemProperty<T> property);

    <T extends ItemPropertyData<T>> void setProperty(@NotNull ItemProperty<T> property, @NotNull T data);

    <T extends ItemPropertyData<T>> void changeProperty(@NotNull ItemProperty<T> property, @NotNull Consumer<T> data);

    boolean isEmpty();

    /**
     * Gets whether the item stack is a food item.
     *
     * @return True if the item can be consumed, otherwise false.
     */
    boolean isEdible();

    /**
     * Gets whether the item is stackable or not.
     *
     * @return True if stackable, otherwise false.
     */
    boolean isStackable();

    // region general item properties

    /**
     * Gets the current name of the item stack.
     *
     * @return The current name.
     */
    @NotNull Component getName();

    /**
     * Changes the name of the item stack.
     *
     * @param name The new name.
     */
    default void setName(@NotNull String name) {
        setName(Component.text(name));
    }

    /**
     * Changes the name of the item stack.
     *
     * @param name The new name.
     */
    void setName(@NotNull Component name);

    /**
     * Resets the name of the item stack to the name of the item.
     */
    void removeCustomName();

    /**
     * Checks whether the item stack has a custom name.
     *
     * @return True if the names isn't equal to the default minecraft name.
     */
    boolean hasCustomName();

    /**
     * Gets the chat hover text for the item stack.
     *
     * @return The hover text.
     */
    @NotNull Component getHoverText();

    /**
     * Gets the lore of the item stack.
     *
     * @return The lore of the item stack.
     */
    @NotNull List<Component> getLore();

    /**
     * Sets the lore of the item stack.
     *
     * @param components The new lore of the item stack.
     */
    void setLore(@NotNull List<Component> components);

    /**
     * Appends extra lore to the current lore.
     *
     * @param lore The lore to add.
     */
    default void appendLore(String... lore) {
        Arrays.stream(lore).forEach(l -> appendLore(Component.text(l)));
    }

    /**
     * Appends extra lore to the current lore.
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
     * Gets the enchantments of the item stack.
     *
     * @return Map of enchantment and level.
     */
    @NotNull Map<Enchantment, Integer> getEnchantments();

    /**
     * Sets the enchantments of the item stack.
     *
     * @param enchantments The enchantments.
     */
    void setEnchantments(@NotNull Map<Enchantment, Integer> enchantments);

    /**
     * Gets the level of a specific enchantment on the item stack.
     *
     * @param enchantment The enchantment to get the level for.
     * @return The level of the enchantment. 0 if the enchantment is not applied to the item stack.
     */
    int getEnchantmentLevel(Enchantment enchantment);

    /**
     * Adds an enchantment to the item stack.
     *
     * @param enchantment The enchantment to add.
     * @param level The level of the enchantment.
     */
    void addEnchantment(@NotNull Enchantment enchantment, Integer level);

    /**
     * Removes an enchantment of the item stack.
     *
     * @param enchantment The enchantment to remove.
     */
    void removeEnchantment(@NotNull Enchantment enchantment);

    /**
     * Removes all enchantments of the item stack.
     */
    void clearEnchantments();

    /**
     * Checks whether the item stack has a specific enchantment.
     *
     * @param enchantment The enchantment to check for.
     * @return True if the enchantment is applied to the item stack.
     */
    boolean hasEnchantment(@NotNull Enchantment enchantment);

    // endregion general item properties

    /**
     * Builder for {@link ItemStack}s.
     */
    interface Builder extends BuilderBase<ItemStack, Builder> {

        Builder type(@NotNull ItemType type);

        Builder amount(int amount);

        Builder name(@NotNull String name);

        Builder name(@NotNull Component name);

        default Builder lore(@NotNull String... lore) {
            return lore((Component[]) Arrays.stream(lore).map(Component::text).toArray());
        }

        Builder lore(@NotNull Component... lore);

        Builder appendLore(@NotNull String... lore);

        Builder appendLore(@NotNull Component... lore);

        Builder removeLoreLine(int line);

        Builder enchant(@NotNull Enchantment enchantment, int level);

        <T extends ItemPropertyData<T>> Builder property(@NotNull ItemProperty<T> property, @NotNull Consumer<T> data);

    }
}
