package org.loomdev.api.world.biome;

import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.util.registry.Keyed;
import org.loomdev.api.village.VillagerVariant;

public interface BiomeType extends Keyed {

    // region :: BiomeTypes

    BiomeType OCEAN = getById("minecraft:ocean");
    BiomeType PLAINS = getById("minecraft:plains");
    BiomeType DESERT = getById("minecraft:desert");
    BiomeType MOUNTAINS = getById("minecraft:mountains");
    BiomeType FOREST = getById("minecraft:forest");
    BiomeType TAIGA = getById("minecraft:taiga");
    BiomeType SWAMP = getById("minecraft:swamp");
    BiomeType RIVER = getById("minecraft:river");
    BiomeType NETHER_WASTES = getById("minecraft:nether_wastes");
    BiomeType THE_END = getById("minecraft:the_end");
    BiomeType FROZEN_OCEAN = getById("minecraft:frozen_ocean");
    BiomeType FROZEN_RIVER = getById("minecraft:frozen_river");
    BiomeType SNOWY_TUNDRA = getById("minecraft:snowy_tundra");
    BiomeType SNOWY_MOUNTAINS = getById("minecraft:snowy_mountains");
    BiomeType MUSHROOM_FIELDS = getById("minecraft:mushroom_fields");
    BiomeType MUSHROOM_FIELD_SHORE = getById("minecraft:mushroom_field_shore");
    BiomeType BEACH = getById("minecraft:beach");
    BiomeType DESERT_HILLS = getById("minecraft:desert_hills");
    BiomeType WOODED_HILLS = getById("minecraft:wooded_hills");
    BiomeType TAIGA_HILLS = getById("minecraft:taiga_hills");
    BiomeType MOUNTAIN_EDGE = getById("minecraft:mountain_edge");
    BiomeType JUNGLE = getById("minecraft:jungle");
    BiomeType JUNGLE_HILLS = getById("minecraft:jungle_hills");
    BiomeType JUNGLE_EDGE = getById("minecraft:jungle_edge");
    BiomeType DEEP_OCEAN = getById("minecraft:deep_ocean");
    BiomeType STONE_SHORE = getById("minecraft:stone_shore");
    BiomeType SNOWY_BEACH = getById("minecraft:snowy_beach");
    BiomeType BIRCH_FOREST = getById("minecraft:birch_forest");
    BiomeType BIRCH_FOREST_HILLS = getById("minecraft:birch_forest_hills");
    BiomeType DARK_FOREST = getById("minecraft:dark_forest");
    BiomeType SNOWY_TAIGA = getById("minecraft:snowy_taiga");
    BiomeType SNOWY_TAIGA_HILLS = getById("minecraft:snowy_taiga_hills");
    BiomeType GIANT_TREE_TAIGA = getById("minecraft:giant_tree_taiga");
    BiomeType GIANT_TREE_TAIGA_HILLS = getById("minecraft:giant_tree_taiga_hills");
    BiomeType WOODED_MOUNTAINS = getById("minecraft:wooded_mountains");
    BiomeType SAVANNA = getById("minecraft:savanna");
    BiomeType SAVANNA_PLATEAU = getById("minecraft:savanna_plateau");
    BiomeType BADLANDS = getById("minecraft:badlands");
    BiomeType WOODED_BADLANDS_PLATEAU = getById("minecraft:wooded_badlands_plateau");
    BiomeType BADLANDS_PLATEAU = getById("minecraft:badlands_plateau");
    BiomeType SMALL_END_ISLANDS = getById("minecraft:small_end_islands");
    BiomeType END_MIDLANDS = getById("minecraft:end_midlands");
    BiomeType END_HIGHLANDS = getById("minecraft:end_highlands");
    BiomeType END_BARRENS = getById("minecraft:end_barrens");
    BiomeType WARM_OCEAN = getById("minecraft:warm_ocean");
    BiomeType LUKEWARM_OCEAN = getById("minecraft:lukewarm_ocean");
    BiomeType COLD_OCEAN = getById("minecraft:cold_ocean");
    BiomeType DEEP_WARM_OCEAN = getById("minecraft:deep_warm_ocean");
    BiomeType DEEP_LUKEWARM_OCEAN = getById("minecraft:deep_lukewarm_ocean");
    BiomeType DEEP_COLD_OCEAN = getById("minecraft:deep_cold_ocean");
    BiomeType DEEP_FROZEN_OCEAN = getById("minecraft:deep_frozen_ocean");
    BiomeType THE_VOID = getById("minecraft:the_void");
    BiomeType SUNFLOWER_PLAINS = getById("minecraft:sunflower_plains");
    BiomeType DESERT_LAKES = getById("minecraft:desert_lakes");
    BiomeType GRAVELLY_MOUNTAINS = getById("minecraft:gravelly_mountains");
    BiomeType FLOWER_FOREST = getById("minecraft:flower_forest");
    BiomeType TAIGA_MOUNTAINS = getById("minecraft:taiga_mountains");
    BiomeType SWAMP_HILLS = getById("minecraft:swamp_hills");
    BiomeType ICE_SPIKES = getById("minecraft:ice_spikes");
    BiomeType MODIFIED_JUNGLE = getById("minecraft:modified_jungle");
    BiomeType MODIFIED_JUNGLE_EDGE = getById("minecraft:modified_jungle_edge");
    BiomeType TALL_BIRCH_FOREST = getById("minecraft:tall_birch_forest");
    BiomeType TALL_BIRCH_HILLS = getById("minecraft:tall_birch_hills");
    BiomeType DARK_FOREST_HILLS = getById("minecraft:dark_forest_hills");
    BiomeType SNOWY_TAIGA_MOUNTAINS = getById("minecraft:snowy_taiga_mountains");
    BiomeType GIANT_SPRUCE_TAIGA = getById("minecraft:giant_spruce_taiga");
    BiomeType GIANT_SPRUCE_TAIGA_HILLS = getById("minecraft:giant_spruce_taiga_hills");
    BiomeType MODIFIED_GRAVELLY_MOUNTAINS = getById("minecraft:modified_gravelly_mountains");
    BiomeType SHATTERED_SAVANNA = getById("minecraft:shattered_savanna");
    BiomeType SHATTERED_SAVANNA_PLATEAU = getById("minecraft:shattered_savanna_plateau");
    BiomeType ERODED_BADLANDS = getById("minecraft:eroded_badlands");
    BiomeType MODIFIED_WOODED_BADLANDS_PLATEAU = getById("minecraft:modified_wooded_badlands_plateau");
    BiomeType MODIFIED_BADLANDS_PLATEAU = getById("minecraft:modified_badlands_plateau");
    BiomeType BAMBOO_JUNGLE = getById("minecraft:bamboo_jungle");
    BiomeType BAMBOO_JUNGLE_HILLS = getById("minecraft:bamboo_jungle_hills");
    BiomeType CRIMSON_FOREST = getById("minecraft:crimson_forest");
    BiomeType WARPED_FOREST = getById("minecraft:warped_forest");
    BiomeType SOUL_SAND_VALLEY = getById("minecraft:soul_sand_valley");
    BiomeType BASALT_DELTAS = getById("minecraft:basalt_deltas");

    // endregion :: BiomeTypes

    static BiomeType getById(String id) {
        return Loom.getRegistry().getWrapped(BiomeType.class, id);
    }

    @Nullable
    VillagerVariant getVillagerVariant();

}
