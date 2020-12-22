package org.loomdev.api.tag;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.util.NamespacedKey;

import java.util.Optional;

public interface EntityTypeTag extends Tag<EntityType<?>> {

    // region :: EntityTypeTags
    EntityTypeTag SKELETONS = getById(NamespacedKey.forMinecraft("skeletons")).get();
    EntityTypeTag RAIDERS = getById(NamespacedKey.forMinecraft("raiders")).get();
    EntityTypeTag BEEHIVE_INHABITORS = getById(NamespacedKey.forMinecraft("beehive_inhabitors")).get();
    EntityTypeTag ARROWS = getById(NamespacedKey.forMinecraft("arrows")).get();
    EntityTypeTag IMPACT_PROJECTILES = getById(NamespacedKey.forMinecraft("impact_projectiles")).get();
    EntityTypeTag POWDER_SNOW_WALKABLE_MOBS = getById(NamespacedKey.forMinecraft("powder_snow_walkable_mobs")).get();
    EntityTypeTag AXOLOTL_ALWAYS_HOSTILES = getById(NamespacedKey.forMinecraft("axolotl_always_hostiles")).get();
    EntityTypeTag AXOLOTL_TEMPTED_HOSTILES = getById(NamespacedKey.forMinecraft("axolotl_tempted_hostiles")).get();
    // endregion :: EntityTypeTags

    static Optional<EntityTypeTag> getById(@NotNull NamespacedKey key) {
        return Loom.getRegistry().getTag(EntityType.class, key);
    }
}
