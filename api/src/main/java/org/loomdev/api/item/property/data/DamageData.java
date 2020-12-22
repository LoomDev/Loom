package org.loomdev.api.item.property.data;

/**
 * Damage data of item stacks
 */
public interface DamageData extends ItemPropertyData<DamageData> {

    /**
     * Gets the amount of damage that has been dealt to the item stack.
     *
     * @return The damage that has been dealt to the item stack.
     */
    int getDamage();

    /**
     * Sets the amount of damage that is dealt to the item stack.
     *
     * @param amount The amount of damage
     */
    void setDamage(int amount);

    /**
     * Increment the damage dealt to the item stack by a specific amount.
     *
     * @param amount The amount to increment the damage with.
     */
    void increment(int amount);

    /**
     * Decrement the damage dealt to the item stack by a specific amount.
     *
     * @param amount The amount to decrement the damage with.
     */
    void decrement(int amount);

    /**
     * Gets the maximum amount of damage that can be dealt to the item stack.
     *
     * @return The maximum amount of damage.
     */
    int getMaxDamage();

    /**
     * Check if the item stack is unbreakable.
     *
     * @return True if the item stack is unbreakable.
     */
    boolean isUnbreakable();

    /**
     * Mark an item stack unbreakable.
     *
     * @param flag Whether the item stack should be unbreakable or not.
     */
    void setUnbreakable(boolean flag);
}
