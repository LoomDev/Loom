package org.loomdev.api.item.property;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.item.property.data.*;

public interface ItemProperty<T extends ItemPropertyData<T>> {

    // region ItemProperties

    /**
     * Change the name of an {@link org.loomdev.api.item.ItemStack}.
     * <p>Can be applied to all {@link ItemType}s.</p>
     */
    ItemProperty<NameData> Name = Loom.getRegistry().getItemProperty(NameData.class);

    /**
     * Change the lore of an {@link org.loomdev.api.item.ItemStack}.
     * <p>Can be applied to all {@link ItemType}s.</p>
     */
    ItemProperty<LoreData> Lore = Loom.getRegistry().getItemProperty(LoreData.class);

    /**
     * Modify the damage properties of an {@link org.loomdev.api.item.ItemStack}.
     * <p>Can be applied to all {@link ItemType}s.</p>
     */
    ItemProperty<DamageData> Damage = Loom.getRegistry().getItemProperty(DamageData.class);

    /**
     * Add, remove, etc. enchantments of an {@link org.loomdev.api.item.ItemStack}.
     * <p>Can be applied to all {@link ItemType}s.</p>
     */
    ItemProperty<EnchantmentData> Enchantments = Loom.getRegistry().getItemProperty(EnchantmentData.class);

    /**
     *  Get or modify the repair cost of an {@link org.loomdev.api.item.ItemStack}.
     *  <p>Can be applied to all repairable Items</p>
     */
    ItemProperty<RepairableData> Repairable = Loom.getRegistry().getItemProperty(RepairableData.class);

    // banner
    // block data
    // block state
    // book
    // Compass
    // Crossbow
    // FireworkStar
    // Firework
    // KnowledgeBook
    // LeatherArmor
    // Map
    // Potion
    // Repairable
    // Skull
    // SpawnEgg
    // SuspiciousStew
    // TropicalFishBucket
    // attribute modifier
    // model data?
    // Item flags (hide stuff, unbreakable?)
    // Persistence data

    // endregion ItemProperties

    /**
     * Gets the {@link ItemPropertyData} object linked to the {@link ItemProperty} of this {@link ItemStack}
     *
     * @param itemStack The {@link ItemStack} the get the {@link ItemPropertyData} object for.
     * @return The {@link ItemPropertyData} object or null if nnot
     */
    @Nullable  T get(@NotNull ItemStack itemStack);

    /**
     * Apply {@link ItemPropertyData} to an {@link ItemStack}.
     *
     * @param itemStack The {@link ItemStack} to apply the {@link ItemProperty} to.
     * @param data The {@link ItemPropertyData} to apply.
     */
    void apply(@NotNull ItemStack itemStack, @NotNull T data);

    /**
     * Check if the {@link ItemProperty} can be applied to an {@link ItemStack}.
     *
     * @param itemStack The {@link ItemStack} to check the {@link ItemProperty} against.
     * @return True if the {@link ItemProperty van be applied}.
     */
    default boolean canApplyTo(@NotNull ItemStack itemStack) {
        return true;
    }
}
