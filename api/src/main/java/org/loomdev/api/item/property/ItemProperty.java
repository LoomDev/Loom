package org.loomdev.api.item.property;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.item.property.data.*;
import org.loomdev.api.util.registry.Registry;

@SuppressWarnings("ConstantConditions")
public interface ItemProperty<T extends ItemPropertyData<T>> {

    // region ItemProperties

    /**
     * Change the name of an {@link ItemStack}.
     * <p>Can be applied to all {@link ItemType}s.</p>
     */
    @NotNull
    ItemProperty<NameData> Name = Registry.get().getItemProperty(NameData.class);

    /**
     * Change the lore of an {@link ItemStack}.
     * <p>Can be applied to all {@link ItemType}s.</p>
     */
    @NotNull
    ItemProperty<LoreData> Lore = Registry.get().getItemProperty(LoreData.class);

    /**
     * Modify the damage properties of an {@link ItemStack}.
     * <p>Can be applied to all {@link ItemType}s.</p>
     */
    @NotNull
    ItemProperty<DamageData> Damage = Registry.get().getItemProperty(DamageData.class);

    /**
     * Add, remove, etc. enchantments of an {@link ItemStack}.
     * <p>Can be applied to all {@link ItemType}s.</p>
     */
    @NotNull
    ItemProperty<EnchantmentData> Enchantments = Registry.get().getItemProperty(EnchantmentData.class);

    /**
     *  Get or modify the repair cost of an {@link ItemStack}.
     *  <p>Can be applied to all repairable Items</p>
     */
    @NotNull
    ItemProperty<RepairableData> Repairable = Registry.get().getItemProperty(RepairableData.class);

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
     * @return {@code true} if the {@link ItemProperty} can be applied.
     */
    default boolean canApplyTo(@NotNull ItemStack itemStack) {
        return true;
    }
}
