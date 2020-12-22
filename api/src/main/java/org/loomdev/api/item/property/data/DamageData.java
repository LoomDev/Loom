package org.loomdev.api.item.property.data;

/**
 * Damage data of {@link org.loomdev.api.item.ItemStack}s
 */
public interface DamageData extends ItemPropertyData<DamageData> {

    /**
     * Gets the amount of damage that has been dealt to the {@link org.loomdev.api.item.ItemStack}.
     *
     * @return The damage that has been dealt to the {@link org.loomdev.api.item.ItemStack}.
     */
    int getDamage();

    /**
     * Sets the amount of damage that is dealt to the {@link org.loomdev.api.item.ItemStack}.
     *
     * @param amount The amount of damage
     */
    void setDamage(int amount);

    /**
     * Increment the damage dealt to the {@link org.loomdev.api.item.ItemStack} by a specific amount.
     *
     * @param amount The amount to increment the damage with.
     */
    void increment(int amount);

    /**
     * Decrement the damage dealt to the {@link org.loomdev.api.item.ItemStack} by a specific amount.
     *
     * @param amount The amount to decrement the damage with.
     */
    void decrement(int amount);

    /**
     * Gets the maximum amount of damage that can be dealt to the {@link org.loomdev.api.item.ItemStack}.
     *
     * @return The maximum amount of damage.
     */
    int getMaxDamage();

    /**
     * Check if the {@link org.loomdev.api.item.ItemStack} is unbreakable.
     *
     * @return True if the {@link org.loomdev.api.item.ItemStack} is unbreakable.
     */
    boolean isUnbreakable();

    /**
     * Mark an {@link org.loomdev.api.item.ItemStack} unbreakable.
     *
     * @param flag Whether the {@link org.loomdev.api.item.ItemStack} should be unbreakable or not.
     */
    void setUnbreakable(boolean flag);
}
