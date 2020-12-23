package org.loomdev.api.item.property.data;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.Enchantment;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.item.ItemStack;

import java.util.Map;

/**
 * Enchantment data of a {@link ItemStack}s.
 *
 * <p>
 *     Note: When used on an {@link ItemType#ENCHANTED_BOOK}
 *     the enchantments will be added as a stored enchantment.
 * </p>
 */
public interface EnchantmentData extends ItemPropertyData<EnchantmentData> {

    /**
     * Gets the enchantments of the {@link ItemStack}.
     *
     * @return Map of enchantment and level.
     */
    @NotNull Map<Enchantment, Integer> getEnchantments();

    /**
     * Sets the enchantments of the {@link ItemStack}.
     *
     * @param enchantments The enchantments.
     */
    void setEnchantments(@NotNull Map<Enchantment, Integer> enchantments);

    /**
     * Gets the level of a specific enchantment on the {@link ItemStack}.
     *
     * @param enchantment The enchantment to get the level for.
     * @return The level of the enchantment. 0 if the enchantment is not applied to the {@link ItemStack}.
     */
    int getEnchantmentLevel(Enchantment enchantment);

    /**
     * Add an enchantment to the {@link ItemStack}.
     *
     * @param enchantment The enchantment to add.
     * @param level The level of the enchantment.
     */
    void addEnchantment(@NotNull Enchantment enchantment, Integer level);

    /**
     * Remove an enchantment of the {@link ItemStack}.
     *
     * @param enchantment The enchantment to remove.
     */
    void removeEnchantment(@NotNull Enchantment enchantment);

    /**
     * Remove all enchantments of the {@link ItemStack}.
     */
    void clearEnchantments();

    /**
     * Check whether the {@link ItemStack} has a specific enchantment.
     *
     * @param enchantment The enchantment to check for.
     * @return {@code true} if the enchantment is applied to the {@link ItemStack}.
     */
    boolean hasEnchantment(@NotNull Enchantment enchantment);

}
