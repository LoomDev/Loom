package org.loomdev.api.container;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.util.registry.Keyed;

public interface ContainerType extends Keyed {

    // region :: ContainerTypes
    ContainerType BREWING_STAND = getById(NamespacedKey.minecraft("brewing_stand"));
    ContainerType ENCHANTMENT = getById(NamespacedKey.minecraft("enchantment"));
    ContainerType LOOM = getById(NamespacedKey.minecraft("loom"));
    ContainerType BLAST_FURNACE = getById(NamespacedKey.minecraft("blast_furnace"));
    ContainerType SMOKER = getById(NamespacedKey.minecraft("smoker"));
    ContainerType SMITHING = getById(NamespacedKey.minecraft("smithing"));
    ContainerType CARTOGRAPHY_TABLE = getById(NamespacedKey.minecraft("cartography_table"));
    ContainerType BEACON = getById(NamespacedKey.minecraft("beacon"));
    ContainerType HOPPER = getById(NamespacedKey.minecraft("hopper"));
    ContainerType MERCHANT = getById(NamespacedKey.minecraft("merchant"));
    ContainerType FURNACE = getById(NamespacedKey.minecraft("furnace"));
    ContainerType ANVIL = getById(NamespacedKey.minecraft("anvil"));
    ContainerType STONECUTTER = getById(NamespacedKey.minecraft("stonecutter"));
    ContainerType GENERIC_3X3 = getById(NamespacedKey.minecraft("generic_3x3"));
    ContainerType CRAFTING = getById(NamespacedKey.minecraft("crafting"));
    ContainerType LECTERN = getById(NamespacedKey.minecraft("lectern"));
    ContainerType GRINDSTONE = getById(NamespacedKey.minecraft("grindstone"));
    ContainerType SHULKER_BOX = getById(NamespacedKey.minecraft("shulker_box"));
    ContainerType GENERIC_9X1 = getById(NamespacedKey.minecraft("generic_9x1"));
    ContainerType GENERIC_9X2 = getById(NamespacedKey.minecraft("generic_9x2"));
    ContainerType GENERIC_9X3 = getById(NamespacedKey.minecraft("generic_9x3"));
    ContainerType GENERIC_9X4 = getById(NamespacedKey.minecraft("generic_9x4"));
    ContainerType GENERIC_9X5 = getById(NamespacedKey.minecraft("generic_9x5"));
    ContainerType GENERIC_9X6 = getById(NamespacedKey.minecraft("generic_9x6"));
    // endregion :: ContainerTypes

    static ContainerType getById(@NotNull NamespacedKey key) {
        return Loom.getRegistry().getWrapped(ContainerType.class, key.toString());
    }
}
