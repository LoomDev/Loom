package org.loomdev.api.item.property.data;

import org.loomdev.api.item.ItemStack;

/**
 * Damage data of {@link ItemStack}s.
 */
public interface DamageData extends ItemPropertyData<DamageData> {

    /**
     * Gets the amount of damage that has been dealt to the {@link ItemStack}.
     *
     * @return The damage that has been dealt to the {@link ItemStack}.
     */
    int getDamage();

    /**
     * Sets the amount of damage that is dealt to the {@link ItemStack}.
     *
     * @param amount The amount of damage
     */
    void setDamage(int amount);

    /**
     * Increment the damage dealt to the {@link ItemStack} by a specific amount.
     *
     * @param amount The amount to increment the damage with.
     */
    void increment(int amount);

    /**
     * Decrement the damage dealt to the {@link ItemStack} by a specific amount.
     *
     * @param amount The amount to decrement the damage with.
     */
    void decrement(int amount);

    /**
     * Gets the maximum amount of damage that can be dealt to the {@link ItemStack}.
     *
     * @return The maximum amount of damage.
     */
    int getMaxDamage();

    /**
     * Check if the {@link ItemStack} is unbreakable.
     *
     * @return {@code true} if the {@link ItemStack} is unbreakable.
     */
    boolean isUnbreakable();

    /**
     * Mark an item stack unbreakable.
     *
     * @param unbreakable Whether the {@link ItemStack} should be unbreakable or not.
     */
    void setUnbreakable(boolean unbreakable);
}
