package org.loomdev.api.tag;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.util.registry.Registry;

import java.util.Optional;

public interface EntityTypeTag extends Tag<EntityType<?>> {

    // region :: EntityTypeTags
    EntityTypeTag SKELETONS = getById(NamespacedKey.minecraft("skeletons")).get();
    EntityTypeTag RAIDERS = getById(NamespacedKey.minecraft("raiders")).get();
    EntityTypeTag BEEHIVE_INHABITORS = getById(NamespacedKey.minecraft("beehive_inhabitors")).get();
    EntityTypeTag ARROWS = getById(NamespacedKey.minecraft("arrows")).get();
    EntityTypeTag IMPACT_PROJECTILES = getById(NamespacedKey.minecraft("impact_projectiles")).get();
    EntityTypeTag POWDER_SNOW_WALKABLE_MOBS = getById(NamespacedKey.minecraft("powder_snow_walkable_mobs")).get();
    EntityTypeTag AXOLOTL_ALWAYS_HOSTILES = getById(NamespacedKey.minecraft("axolotl_always_hostiles")).get();
    EntityTypeTag AXOLOTL_TEMPTED_HOSTILES = getById(NamespacedKey.minecraft("axolotl_tempted_hostiles")).get();
    // endregion :: EntityTypeTags

    static Optional<EntityTypeTag> getById(@NotNull NamespacedKey key) {
        return Registry.get().getTag(EntityType.class, key);
    }
}
