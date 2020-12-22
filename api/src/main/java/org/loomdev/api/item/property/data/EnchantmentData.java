package org.loomdev.api.item.property.data;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.Enchantment;
import org.loomdev.api.item.ItemType;

import java.util.Map;

/**
 * Enchantment data of an item stack.
 *
 * <p>
 *     Note: When used on an {@link ItemType#ENCHANTED_BOOK}
 *     the enchantments will be added as a stored enchantment.
 * </p>
 */
public interface EnchantmentData extends ItemPropertyData<EnchantmentData> {

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
     * Add an enchantment to the item stack.
     *
     * @param enchantment The enchantment to add.
     * @param level The level of the enchantment.
     */
    void addEnchantment(@NotNull Enchantment enchantment, Integer level);

    /**
     * Remove an enchantment of the item stack.
     *
     * @param enchantment The enchantment to remove.
     */
    void removeEnchantment(@NotNull Enchantment enchantment);

    /**
     * Remove all enchantments of the item stack.
     */
    void clearEnchantments();

    /**
     * Check whether the item stack has a specific enchantment.
     *
     * @param enchantment The enchantment to check for.
     * @return True if the enchantment is applied to the item stack.
     */
    boolean hasEnchantment(@NotNull Enchantment enchantment);

}
