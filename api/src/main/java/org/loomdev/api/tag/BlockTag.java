package org.loomdev.api.tag;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.util.NamespacedKey;

import java.util.Optional;

public interface BlockTag extends Tag<BlockType> {

    // region :: BlockTags
    BlockTag WOOL = getById(NamespacedKey.forMinecraft("wool")).get();
    BlockTag PLANKS = getById(NamespacedKey.forMinecraft("planks")).get();
    BlockTag STONE_BRICKS = getById(NamespacedKey.forMinecraft("stone_bricks")).get();
    BlockTag WOODEN_BUTTONS = getById(NamespacedKey.forMinecraft("wooden_buttons")).get();
    BlockTag BUTTONS = getById(NamespacedKey.forMinecraft("buttons")).get();
    BlockTag CARPETS = getById(NamespacedKey.forMinecraft("carpets")).get();
    BlockTag WOODEN_DOORS = getById(NamespacedKey.forMinecraft("wooden_doors")).get();
    BlockTag WOODEN_STAIRS = getById(NamespacedKey.forMinecraft("wooden_stairs")).get();
    BlockTag WOODEN_SLABS = getById(NamespacedKey.forMinecraft("wooden_slabs")).get();
    BlockTag WOODEN_FENCES = getById(NamespacedKey.forMinecraft("wooden_fences")).get();
    BlockTag PRESSURE_PLATES = getById(NamespacedKey.forMinecraft("pressure_plates")).get();
    BlockTag WOODEN_PRESSURE_PLATES = getById(NamespacedKey.forMinecraft("wooden_pressure_plates")).get();
    BlockTag STONE_PRESSURE_PLATES = getById(NamespacedKey.forMinecraft("stone_pressure_plates")).get();
    BlockTag WOODEN_TRAPDOORS = getById(NamespacedKey.forMinecraft("wooden_trapdoors")).get();
    BlockTag DOORS = getById(NamespacedKey.forMinecraft("doors")).get();
    BlockTag SAPLINGS = getById(NamespacedKey.forMinecraft("saplings")).get();
    BlockTag LOGS_THAT_BURN = getById(NamespacedKey.forMinecraft("logs_that_burn")).get();
    BlockTag LOGS = getById(NamespacedKey.forMinecraft("logs")).get();
    BlockTag DARK_OAK_LOGS = getById(NamespacedKey.forMinecraft("dark_oak_logs")).get();
    BlockTag OAK_LOGS = getById(NamespacedKey.forMinecraft("oak_logs")).get();
    BlockTag BIRCH_LOGS = getById(NamespacedKey.forMinecraft("birch_logs")).get();
    BlockTag ACACIA_LOGS = getById(NamespacedKey.forMinecraft("acacia_logs")).get();
    BlockTag JUNGLE_LOGS = getById(NamespacedKey.forMinecraft("jungle_logs")).get();
    BlockTag SPRUCE_LOGS = getById(NamespacedKey.forMinecraft("spruce_logs")).get();
    BlockTag CRIMSON_STEMS = getById(NamespacedKey.forMinecraft("crimson_stems")).get();
    BlockTag WARPED_STEMS = getById(NamespacedKey.forMinecraft("warped_stems")).get();
    BlockTag BANNERS = getById(NamespacedKey.forMinecraft("banners")).get();
    BlockTag SAND = getById(NamespacedKey.forMinecraft("sand")).get();
    BlockTag STAIRS = getById(NamespacedKey.forMinecraft("stairs")).get();
    BlockTag SLABS = getById(NamespacedKey.forMinecraft("slabs")).get();
    BlockTag WALLS = getById(NamespacedKey.forMinecraft("walls")).get();
    BlockTag ANVIL = getById(NamespacedKey.forMinecraft("anvil")).get();
    BlockTag RAILS = getById(NamespacedKey.forMinecraft("rails")).get();
    BlockTag LEAVES = getById(NamespacedKey.forMinecraft("leaves")).get();
    BlockTag TRAPDOORS = getById(NamespacedKey.forMinecraft("trapdoors")).get();
    BlockTag SMALL_FLOWERS = getById(NamespacedKey.forMinecraft("small_flowers")).get();
    BlockTag BEDS = getById(NamespacedKey.forMinecraft("beds")).get();
    BlockTag FENCES = getById(NamespacedKey.forMinecraft("fences")).get();
    BlockTag TALL_FLOWERS = getById(NamespacedKey.forMinecraft("tall_flowers")).get();
    BlockTag FLOWERS = getById(NamespacedKey.forMinecraft("flowers")).get();
    BlockTag PIGLIN_REPELLENTS = getById(NamespacedKey.forMinecraft("piglin_repellents")).get();
    BlockTag GOLD_ORES = getById(NamespacedKey.forMinecraft("gold_ores")).get();
    BlockTag NON_FLAMMABLE_WOOD = getById(NamespacedKey.forMinecraft("non_flammable_wood")).get();
    BlockTag CANDLES = getById(NamespacedKey.forMinecraft("candles")).get();
    BlockTag FLOWER_POTS = getById(NamespacedKey.forMinecraft("flower_pots")).get();
    BlockTag ENDERMAN_HOLDABLE = getById(NamespacedKey.forMinecraft("enderman_holdable")).get();
    BlockTag ICE = getById(NamespacedKey.forMinecraft("ice")).get();
    BlockTag VALID_SPAWN = getById(NamespacedKey.forMinecraft("valid_spawn")).get();
    BlockTag IMPERMEABLE = getById(NamespacedKey.forMinecraft("impermeable")).get();
    BlockTag UNDERWATER_BONEMEALS = getById(NamespacedKey.forMinecraft("underwater_bonemeals")).get();
    BlockTag CORAL_BLOCKS = getById(NamespacedKey.forMinecraft("coral_blocks")).get();
    BlockTag WALL_CORALS = getById(NamespacedKey.forMinecraft("wall_corals")).get();
    BlockTag CORAL_PLANTS = getById(NamespacedKey.forMinecraft("coral_plants")).get();
    BlockTag CORALS = getById(NamespacedKey.forMinecraft("corals")).get();
    BlockTag BAMBOO_PLANTABLE_ON = getById(NamespacedKey.forMinecraft("bamboo_plantable_on")).get();
    BlockTag STANDING_SIGNS = getById(NamespacedKey.forMinecraft("standing_signs")).get();
    BlockTag WALL_SIGNS = getById(NamespacedKey.forMinecraft("wall_signs")).get();
    BlockTag SIGNS = getById(NamespacedKey.forMinecraft("signs")).get();
    BlockTag DRAGON_IMMUNE = getById(NamespacedKey.forMinecraft("dragon_immune")).get();
    BlockTag WITHER_IMMUNE = getById(NamespacedKey.forMinecraft("wither_immune")).get();
    BlockTag WITHER_SUMMON_BASE_BLOCKS = getById(NamespacedKey.forMinecraft("wither_summon_base_blocks")).get();
    BlockTag BEEHIVES = getById(NamespacedKey.forMinecraft("beehives")).get();
    BlockTag CROPS = getById(NamespacedKey.forMinecraft("crops")).get();
    BlockTag BEE_GROWABLES = getById(NamespacedKey.forMinecraft("bee_growables")).get();
    BlockTag PORTALS = getById(NamespacedKey.forMinecraft("portals")).get();
    BlockTag FIRE = getById(NamespacedKey.forMinecraft("fire")).get();
    BlockTag NYLIUM = getById(NamespacedKey.forMinecraft("nylium")).get();
    BlockTag WART_BLOCKS = getById(NamespacedKey.forMinecraft("wart_blocks")).get();
    BlockTag BEACON_BASE_BLOCKS = getById(NamespacedKey.forMinecraft("beacon_base_blocks")).get();
    BlockTag SOUL_SPEED_BLOCKS = getById(NamespacedKey.forMinecraft("soul_speed_blocks")).get();
    BlockTag WALL_POST_OVERRIDE = getById(NamespacedKey.forMinecraft("wall_post_override")).get();
    BlockTag CLIMBABLE = getById(NamespacedKey.forMinecraft("climbable")).get();
    BlockTag SHULKER_BOXES = getById(NamespacedKey.forMinecraft("shulker_boxes")).get();
    BlockTag HOGLIN_REPELLENTS = getById(NamespacedKey.forMinecraft("hoglin_repellents")).get();
    BlockTag SOUL_FIRE_BASE_BLOCKS = getById(NamespacedKey.forMinecraft("soul_fire_base_blocks")).get();
    BlockTag STRIDER_WARM_BLOCKS = getById(NamespacedKey.forMinecraft("strider_warm_blocks")).get();
    BlockTag CAMPFIRES = getById(NamespacedKey.forMinecraft("campfires")).get();
    BlockTag GUARDED_BY_PIGLINS = getById(NamespacedKey.forMinecraft("guarded_by_piglins")).get();
    BlockTag PREVENT_MOB_SPAWNING_INSIDE = getById(NamespacedKey.forMinecraft("prevent_mob_spawning_inside")).get();
    BlockTag FENCE_GATES = getById(NamespacedKey.forMinecraft("fence_gates")).get();
    BlockTag UNSTABLE_BOTTOM_CENTER = getById(NamespacedKey.forMinecraft("unstable_bottom_center")).get();
    BlockTag MUSHROOM_GROW_BLOCK = getById(NamespacedKey.forMinecraft("mushroom_grow_block")).get();
    BlockTag INFINIBURN_OVERWORLD = getById(NamespacedKey.forMinecraft("infiniburn_overworld")).get();
    BlockTag INFINIBURN_NETHER = getById(NamespacedKey.forMinecraft("infiniburn_nether")).get();
    BlockTag INFINIBURN_END = getById(NamespacedKey.forMinecraft("infiniburn_end")).get();
    BlockTag BASE_STONE_OVERWORLD = getById(NamespacedKey.forMinecraft("base_stone_overworld")).get();
    BlockTag BASE_STONE_NETHER = getById(NamespacedKey.forMinecraft("base_stone_nether")).get();
    BlockTag CANDLE_CAKES = getById(NamespacedKey.forMinecraft("candle_cakes")).get();
    BlockTag CAULDRONS = getById(NamespacedKey.forMinecraft("cauldrons")).get();
    BlockTag CRYSTAL_SOUND_BLOCKS = getById(NamespacedKey.forMinecraft("crystal_sound_blocks")).get();
    BlockTag INSIDE_STEP_SOUND_BLOCKS = getById(NamespacedKey.forMinecraft("inside_step_sound_blocks")).get();
    BlockTag OCCLUDES_VIBRATION_SIGNALS = getById(NamespacedKey.forMinecraft("occludes_vibration_signals")).get();
    BlockTag DRIPSTONE_REPLACEABLE = getById(NamespacedKey.forMinecraft("dripstone_replaceable_blocks")).get();
    // endregion :: BlockTags

    static Optional<BlockTag> getById(@NotNull NamespacedKey key) {
        return Loom.getRegistry().getTag(BlockType.class, key);
    }
}
