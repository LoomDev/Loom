package org.loomdev.api.tag;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.util.NamespacedKey;

import java.util.Optional;

public interface ItemTag extends Tag<ItemType> {

    // region :: ItemTags
    ItemTag WOOL = getById(NamespacedKey.minecraft("wool")).get();
    ItemTag PLANKS = getById(NamespacedKey.minecraft("planks")).get();
    ItemTag STONE_BRICKS = getById(NamespacedKey.minecraft("stone_bricks")).get();
    ItemTag WOODEN_BUTTONS = getById(NamespacedKey.minecraft("wooden_buttons")).get();
    ItemTag BUTTONS = getById(NamespacedKey.minecraft("buttons")).get();
    ItemTag CARPETS = getById(NamespacedKey.minecraft("carpets")).get();
    ItemTag WOODEN_DOORS = getById(NamespacedKey.minecraft("wooden_doors")).get();
    ItemTag WOODEN_STAIRS = getById(NamespacedKey.minecraft("wooden_stairs")).get();
    ItemTag WOODEN_SLABS = getById(NamespacedKey.minecraft("wooden_slabs")).get();
    ItemTag WOODEN_FENCES = getById(NamespacedKey.minecraft("wooden_fences")).get();
    ItemTag WOODEN_PRESSURE_PLATES = getById(NamespacedKey.minecraft("wooden_pressure_plates")).get();
    ItemTag WOODEN_TRAPDOORS = getById(NamespacedKey.minecraft("wooden_trapdoors")).get();
    ItemTag DOORS = getById(NamespacedKey.minecraft("doors")).get();
    ItemTag SAPLINGS = getById(NamespacedKey.minecraft("saplings")).get();
    ItemTag LOGS_THAT_BURN = getById(NamespacedKey.minecraft("logs_that_burn")).get();
    ItemTag LOGS = getById(NamespacedKey.minecraft("logs")).get();
    ItemTag DARK_OAK_LOGS = getById(NamespacedKey.minecraft("dark_oak_logs")).get();
    ItemTag OAK_LOGS = getById(NamespacedKey.minecraft("oak_logs")).get();
    ItemTag BIRCH_LOGS = getById(NamespacedKey.minecraft("birch_logs")).get();
    ItemTag ACACIA_LOGS = getById(NamespacedKey.minecraft("acacia_logs")).get();
    ItemTag JUNGLE_LOGS = getById(NamespacedKey.minecraft("jungle_logs")).get();
    ItemTag SPRUCE_LOGS = getById(NamespacedKey.minecraft("spruce_logs")).get();
    ItemTag CRIMSON_STEMS = getById(NamespacedKey.minecraft("crimson_stems")).get();
    ItemTag WARPED_STEMS = getById(NamespacedKey.minecraft("warped_stems")).get();
    ItemTag BANNERS = getById(NamespacedKey.minecraft("banners")).get();
    ItemTag SAND = getById(NamespacedKey.minecraft("sand")).get();
    ItemTag STAIRS = getById(NamespacedKey.minecraft("stairs")).get();
    ItemTag SLABS = getById(NamespacedKey.minecraft("slabs")).get();
    ItemTag WALLS = getById(NamespacedKey.minecraft("walls")).get();
    ItemTag ANVIL = getById(NamespacedKey.minecraft("anvil")).get();
    ItemTag RAILS = getById(NamespacedKey.minecraft("rails")).get();
    ItemTag LEAVES = getById(NamespacedKey.minecraft("leaves")).get();
    ItemTag TRAPDOORS = getById(NamespacedKey.minecraft("trapdoors")).get();
    ItemTag SMALL_FLOWERS = getById(NamespacedKey.minecraft("small_flowers")).get();
    ItemTag BEDS = getById(NamespacedKey.minecraft("beds")).get();
    ItemTag FENCES = getById(NamespacedKey.minecraft("fences")).get();
    ItemTag TALL_FLOWERS = getById(NamespacedKey.minecraft("tall_flowers")).get();
    ItemTag FLOWERS = getById(NamespacedKey.minecraft("flowers")).get();
    ItemTag PIGLIN_REPELLENTS = getById(NamespacedKey.minecraft("piglin_repellents")).get();
    ItemTag PIGLIN_LOVED = getById(NamespacedKey.minecraft("piglin_loved")).get();
    ItemTag IGNORED_BY_PIGLIN_BABIES = getById(NamespacedKey.minecraft("ignored_by_piglin_babies")).get();
    ItemTag PIGLIN_FOOD = getById(NamespacedKey.minecraft("piglin_food")).get();
    ItemTag GOLD_ORES = getById(NamespacedKey.minecraft("gold_ores")).get();
    ItemTag NON_FLAMMABLE_WOOD = getById(NamespacedKey.minecraft("non_flammable_wood")).get();
    ItemTag SOUL_FIRE_BASE_BLOCKS = getById(NamespacedKey.minecraft("soul_fire_base_blocks")).get();
    ItemTag CANDLES = getById(NamespacedKey.minecraft("candles")).get();
    ItemTag BOATS = getById(NamespacedKey.minecraft("boats")).get();
    ItemTag FISHES = getById(NamespacedKey.minecraft("fishes")).get();
    ItemTag SIGNS = getById(NamespacedKey.minecraft("signs")).get();
    ItemTag MUSIC_DISCS = getById(NamespacedKey.minecraft("music_discs")).get();
    ItemTag CREEPER_DROP_MUSIC_DISCS = getById(NamespacedKey.minecraft("creeper_drop_music_discs")).get();
    ItemTag COALS = getById(NamespacedKey.minecraft("coals")).get();
    ItemTag ARROWS = getById(NamespacedKey.minecraft("arrows")).get();
    ItemTag LECTERN_BOOKS = getById(NamespacedKey.minecraft("lectern_books")).get();
    ItemTag BEACON_PAYMENT_ITEMS = getById(NamespacedKey.minecraft("beacon_payment_items")).get();
    ItemTag STONE_TOOL_MATERIALS = getById(NamespacedKey.minecraft("stone_tool_materials")).get();
    ItemTag STONE_CRAFTING_MATERIALS = getById(NamespacedKey.minecraft("stone_crafting_materials")).get();
    ItemTag FREEZE_IMMUNE_WEARABLES = getById(NamespacedKey.minecraft("freeze_immune_wearables")).get();
    ItemTag AXOLOTL_TEMPT_ITEMS = getById(NamespacedKey.minecraft("axolotl_tempt_items")).get();
    ItemTag OCCLUDES_VIBRATION_SIGNALS = getById(NamespacedKey.minecraft("occludes_vibration_signals")).get();
    // endregion :: ItemTags

    static Optional<ItemTag> getById(@NotNull NamespacedKey key) {
        return Loom.getRegistry().getTag(ItemType.class, key);
    }
}
