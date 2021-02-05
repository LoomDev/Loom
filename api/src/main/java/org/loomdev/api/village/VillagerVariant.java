package org.loomdev.api.village;

import org.jetbrains.annotations.Nullable;
import org.loomdev.api.util.registry.Keyed;
import org.loomdev.api.util.registry.Registry;
import org.loomdev.api.world.biome.BiomeType;

public interface VillagerVariant extends Keyed {

    // region :: VillagerVariants

    VillagerVariant DESERT = getById("minecraft:desert");
    VillagerVariant JUNGLE = getById("minecraft:jungle");
    VillagerVariant PLAINS = getById("minecraft:plains");
    VillagerVariant SAVANNA = getById("minecraft:savanna");
    VillagerVariant SNOW = getById("minecraft:snow");
    VillagerVariant SWAMP = getById("minecraft:swamp");
    VillagerVariant TAIGA = getById("minecraft:taiga");

    // endregion :: VillagerVariants

    static VillagerVariant getById(String id) {
        return Registry.get().getWrapped(VillagerVariant.class, id);
    }

    @Nullable
    static VillagerVariant getByBiome(BiomeType biomeType) {
        return biomeType.getVillagerVariant();
    }

}
