package org.loomdev.api.tag;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.util.registry.Registry;

import java.util.Optional;

public interface BlockTag extends Tag<BlockType> {

    // region :: BlockTags
    BlockTag WOOL = getById(NamespacedKey.minecraft("wool")).get();
    BlockTag PLANKS = getById(NamespacedKey.minecraft("planks")).get();
    BlockTag STONE_BRICKS = getById(NamespacedKey.minecraft("stone_bricks")).get();
    BlockTag WOODEN_BUTTONS = getById(NamespacedKey.minecraft("wooden_buttons")).get();
    BlockTag BUTTONS = getById(NamespacedKey.minecraft("buttons")).get();
    BlockTag CARPETS = getById(NamespacedKey.minecraft("carpets")).get();
    BlockTag WOODEN_DOORS = getById(NamespacedKey.minecraft("wooden_doors")).get();
    BlockTag WOODEN_STAIRS = getById(NamespacedKey.minecraft("wooden_stairs")).get();
    BlockTag WOODEN_SLABS = getById(NamespacedKey.minecraft("wooden_slabs")).get();
    BlockTag WOODEN_FENCES = getById(NamespacedKey.minecraft("wooden_fences")).get();
    BlockTag PRESSURE_PLATES = getById(NamespacedKey.minecraft("pressure_plates")).get();
    BlockTag WOODEN_PRESSURE_PLATES = getById(NamespacedKey.minecraft("wooden_pressure_plates")).get();
    BlockTag STONE_PRESSURE_PLATES = getById(NamespacedKey.minecraft("stone_pressure_plates")).get();
    BlockTag WOODEN_TRAPDOORS = getById(NamespacedKey.minecraft("wooden_trapdoors")).get();
    BlockTag DOORS = getById(NamespacedKey.minecraft("doors")).get();
    BlockTag SAPLINGS = getById(NamespacedKey.minecraft("saplings")).get();
    BlockTag LOGS_THAT_BURN = getById(NamespacedKey.minecraft("logs_that_burn")).get();
    BlockTag LOGS = getById(NamespacedKey.minecraft("logs")).get();
    BlockTag DARK_OAK_LOGS = getById(NamespacedKey.minecraft("dark_oak_logs")).get();
    BlockTag OAK_LOGS = getById(NamespacedKey.minecraft("oak_logs")).get();
    BlockTag BIRCH_LOGS = getById(NamespacedKey.minecraft("birch_logs")).get();
    BlockTag ACACIA_LOGS = getById(NamespacedKey.minecraft("acacia_logs")).get();
    BlockTag JUNGLE_LOGS = getById(NamespacedKey.minecraft("jungle_logs")).get();
    BlockTag SPRUCE_LOGS = getById(NamespacedKey.minecraft("spruce_logs")).get();
    BlockTag CRIMSON_STEMS = getById(NamespacedKey.minecraft("crimson_stems")).get();
    BlockTag WARPED_STEMS = getById(NamespacedKey.minecraft("warped_stems")).get();
    BlockTag BANNERS = getById(NamespacedKey.minecraft("banners")).get();
    BlockTag SAND = getById(NamespacedKey.minecraft("sand")).get();
    BlockTag STAIRS = getById(NamespacedKey.minecraft("stairs")).get();
    BlockTag SLABS = getById(NamespacedKey.minecraft("slabs")).get();
    BlockTag WALLS = getById(NamespacedKey.minecraft("walls")).get();
    BlockTag ANVIL = getById(NamespacedKey.minecraft("anvil")).get();
    BlockTag RAILS = getById(NamespacedKey.minecraft("rails")).get();
    BlockTag LEAVES = getById(NamespacedKey.minecraft("leaves")).get();
    BlockTag TRAPDOORS = getById(NamespacedKey.minecraft("trapdoors")).get();
    BlockTag SMALL_FLOWERS = getById(NamespacedKey.minecraft("small_flowers")).get();
    BlockTag BEDS = getById(NamespacedKey.minecraft("beds")).get();
    BlockTag FENCES = getById(NamespacedKey.minecraft("fences")).get();
    BlockTag TALL_FLOWERS = getById(NamespacedKey.minecraft("tall_flowers")).get();
    BlockTag FLOWERS = getById(NamespacedKey.minecraft("flowers")).get();
    BlockTag PIGLIN_REPELLENTS = getById(NamespacedKey.minecraft("piglin_repellents")).get();
    BlockTag GOLD_ORES = getById(NamespacedKey.minecraft("gold_ores")).get();
    BlockTag NON_FLAMMABLE_WOOD = getById(NamespacedKey.minecraft("non_flammable_wood")).get();
    BlockTag CANDLES = getById(NamespacedKey.minecraft("candles")).get();
    BlockTag FLOWER_POTS = getById(NamespacedKey.minecraft("flower_pots")).get();
    BlockTag ENDERMAN_HOLDABLE = getById(NamespacedKey.minecraft("enderman_holdable")).get();
    BlockTag ICE = getById(NamespacedKey.minecraft("ice")).get();
    BlockTag VALID_SPAWN = getById(NamespacedKey.minecraft("valid_spawn")).get();
    BlockTag IMPERMEABLE = getById(NamespacedKey.minecraft("impermeable")).get();
    BlockTag UNDERWATER_BONEMEALS = getById(NamespacedKey.minecraft("underwater_bonemeals")).get();
    BlockTag CORAL_BLOCKS = getById(NamespacedKey.minecraft("coral_blocks")).get();
    BlockTag WALL_CORALS = getById(NamespacedKey.minecraft("wall_corals")).get();
    BlockTag CORAL_PLANTS = getById(NamespacedKey.minecraft("coral_plants")).get();
    BlockTag CORALS = getById(NamespacedKey.minecraft("corals")).get();
    BlockTag BAMBOO_PLANTABLE_ON = getById(NamespacedKey.minecraft("bamboo_plantable_on")).get();
    BlockTag STANDING_SIGNS = getById(NamespacedKey.minecraft("standing_signs")).get();
    BlockTag WALL_SIGNS = getById(NamespacedKey.minecraft("wall_signs")).get();
    BlockTag SIGNS = getById(NamespacedKey.minecraft("signs")).get();
    BlockTag DRAGON_IMMUNE = getById(NamespacedKey.minecraft("dragon_immune")).get();
    BlockTag WITHER_IMMUNE = getById(NamespacedKey.minecraft("wither_immune")).get();
    BlockTag WITHER_SUMMON_BASE_BLOCKS = getById(NamespacedKey.minecraft("wither_summon_base_blocks")).get();
    BlockTag BEEHIVES = getById(NamespacedKey.minecraft("beehives")).get();
    BlockTag CROPS = getById(NamespacedKey.minecraft("crops")).get();
    BlockTag BEE_GROWABLES = getById(NamespacedKey.minecraft("bee_growables")).get();
    BlockTag PORTALS = getById(NamespacedKey.minecraft("portals")).get();
    BlockTag FIRE = getById(NamespacedKey.minecraft("fire")).get();
    BlockTag NYLIUM = getById(NamespacedKey.minecraft("nylium")).get();
    BlockTag WART_BLOCKS = getById(NamespacedKey.minecraft("wart_blocks")).get();
    BlockTag BEACON_BASE_BLOCKS = getById(NamespacedKey.minecraft("beacon_base_blocks")).get();
    BlockTag SOUL_SPEED_BLOCKS = getById(NamespacedKey.minecraft("soul_speed_blocks")).get();
    BlockTag WALL_POST_OVERRIDE = getById(NamespacedKey.minecraft("wall_post_override")).get();
    BlockTag CLIMBABLE = getById(NamespacedKey.minecraft("climbable")).get();
    BlockTag SHULKER_BOXES = getById(NamespacedKey.minecraft("shulker_boxes")).get();
    BlockTag HOGLIN_REPELLENTS = getById(NamespacedKey.minecraft("hoglin_repellents")).get();
    BlockTag SOUL_FIRE_BASE_BLOCKS = getById(NamespacedKey.minecraft("soul_fire_base_blocks")).get();
    BlockTag STRIDER_WARM_BLOCKS = getById(NamespacedKey.minecraft("strider_warm_blocks")).get();
    BlockTag CAMPFIRES = getById(NamespacedKey.minecraft("campfires")).get();
    BlockTag GUARDED_BY_PIGLINS = getById(NamespacedKey.minecraft("guarded_by_piglins")).get();
    BlockTag PREVENT_MOB_SPAWNING_INSIDE = getById(NamespacedKey.minecraft("prevent_mob_spawning_inside")).get();
    BlockTag FENCE_GATES = getById(NamespacedKey.minecraft("fence_gates")).get();
    BlockTag UNSTABLE_BOTTOM_CENTER = getById(NamespacedKey.minecraft("unstable_bottom_center")).get();
    BlockTag MUSHROOM_GROW_BLOCK = getById(NamespacedKey.minecraft("mushroom_grow_block")).get();
    BlockTag INFINIBURN_OVERWORLD = getById(NamespacedKey.minecraft("infiniburn_overworld")).get();
    BlockTag INFINIBURN_NETHER = getById(NamespacedKey.minecraft("infiniburn_nether")).get();
    BlockTag INFINIBURN_END = getById(NamespacedKey.minecraft("infiniburn_end")).get();
    BlockTag BASE_STONE_OVERWORLD = getById(NamespacedKey.minecraft("base_stone_overworld")).get();
    BlockTag BASE_STONE_NETHER = getById(NamespacedKey.minecraft("base_stone_nether")).get();
    BlockTag CANDLE_CAKES = getById(NamespacedKey.minecraft("candle_cakes")).get();
    BlockTag CAULDRONS = getById(NamespacedKey.minecraft("cauldrons")).get();
    BlockTag CRYSTAL_SOUND_BLOCKS = getById(NamespacedKey.minecraft("crystal_sound_blocks")).get();
    BlockTag INSIDE_STEP_SOUND_BLOCKS = getById(NamespacedKey.minecraft("inside_step_sound_blocks")).get();
    BlockTag OCCLUDES_VIBRATION_SIGNALS = getById(NamespacedKey.minecraft("occludes_vibration_signals")).get();
    BlockTag DRIPSTONE_REPLACEABLE = getById(NamespacedKey.minecraft("dripstone_replaceable_blocks")).get();
    // endregion :: BlockTags

    static Optional<BlockTag> getById(@NotNull NamespacedKey key) {
        return Registry.get().getTag(BlockType.class, key);
    }
}
