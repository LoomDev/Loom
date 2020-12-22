package org.loomdev.api.tag;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.util.NamespacedKey;

import java.util.Optional;

public interface ItemTag extends Tag<ItemType> {

    // region :: ItemTags
    ItemTag WOOL = getById(NamespacedKey.forMinecraft("wool")).get();
    ItemTag PLANKS = getById(NamespacedKey.forMinecraft("planks")).get();
    ItemTag STONE_BRICKS = getById(NamespacedKey.forMinecraft("stone_bricks")).get();
    ItemTag WOODEN_BUTTONS = getById(NamespacedKey.forMinecraft("wooden_buttons")).get();
    ItemTag BUTTONS = getById(NamespacedKey.forMinecraft("buttons")).get();
    ItemTag CARPETS = getById(NamespacedKey.forMinecraft("carpets")).get();
    ItemTag WOODEN_DOORS = getById(NamespacedKey.forMinecraft("wooden_doors")).get();
    ItemTag WOODEN_STAIRS = getById(NamespacedKey.forMinecraft("wooden_stairs")).get();
    ItemTag WOODEN_SLABS = getById(NamespacedKey.forMinecraft("wooden_slabs")).get();
    ItemTag WOODEN_FENCES = getById(NamespacedKey.forMinecraft("wooden_fences")).get();
    ItemTag WOODEN_PRESSURE_PLATES = getById(NamespacedKey.forMinecraft("wooden_pressure_plates")).get();
    ItemTag WOODEN_TRAPDOORS = getById(NamespacedKey.forMinecraft("wooden_trapdoors")).get();
    ItemTag DOORS = getById(NamespacedKey.forMinecraft("doors")).get();
    ItemTag SAPLINGS = getById(NamespacedKey.forMinecraft("saplings")).get();
    ItemTag LOGS_THAT_BURN = getById(NamespacedKey.forMinecraft("logs_that_burn")).get();
    ItemTag LOGS = getById(NamespacedKey.forMinecraft("logs")).get();
    ItemTag DARK_OAK_LOGS = getById(NamespacedKey.forMinecraft("dark_oak_logs")).get();
    ItemTag OAK_LOGS = getById(NamespacedKey.forMinecraft("oak_logs")).get();
    ItemTag BIRCH_LOGS = getById(NamespacedKey.forMinecraft("birch_logs")).get();
    ItemTag ACACIA_LOGS = getById(NamespacedKey.forMinecraft("acacia_logs")).get();
    ItemTag JUNGLE_LOGS = getById(NamespacedKey.forMinecraft("jungle_logs")).get();
    ItemTag SPRUCE_LOGS = getById(NamespacedKey.forMinecraft("spruce_logs")).get();
    ItemTag CRIMSON_STEMS = getById(NamespacedKey.forMinecraft("crimson_stems")).get();
    ItemTag WARPED_STEMS = getById(NamespacedKey.forMinecraft("warped_stems")).get();
    ItemTag BANNERS = getById(NamespacedKey.forMinecraft("banners")).get();
    ItemTag SAND = getById(NamespacedKey.forMinecraft("sand")).get();
    ItemTag STAIRS = getById(NamespacedKey.forMinecraft("stairs")).get();
    ItemTag SLABS = getById(NamespacedKey.forMinecraft("slabs")).get();
    ItemTag WALLS = getById(NamespacedKey.forMinecraft("walls")).get();
    ItemTag ANVIL = getById(NamespacedKey.forMinecraft("anvil")).get();
    ItemTag RAILS = getById(NamespacedKey.forMinecraft("rails")).get();
    ItemTag LEAVES = getById(NamespacedKey.forMinecraft("leaves")).get();
    ItemTag TRAPDOORS = getById(NamespacedKey.forMinecraft("trapdoors")).get();
    ItemTag SMALL_FLOWERS = getById(NamespacedKey.forMinecraft("small_flowers")).get();
    ItemTag BEDS = getById(NamespacedKey.forMinecraft("beds")).get();
    ItemTag FENCES = getById(NamespacedKey.forMinecraft("fences")).get();
    ItemTag TALL_FLOWERS = getById(NamespacedKey.forMinecraft("tall_flowers")).get();
    ItemTag FLOWERS = getById(NamespacedKey.forMinecraft("flowers")).get();
    ItemTag PIGLIN_REPELLENTS = getById(NamespacedKey.forMinecraft("piglin_repellents")).get();
    ItemTag PIGLIN_LOVED = getById(NamespacedKey.forMinecraft("piglin_loved")).get();
    ItemTag IGNORED_BY_PIGLIN_BABIES = getById(NamespacedKey.forMinecraft("ignored_by_piglin_babies")).get();
    ItemTag PIGLIN_FOOD = getById(NamespacedKey.forMinecraft("piglin_food")).get();
    ItemTag GOLD_ORES = getById(NamespacedKey.forMinecraft("gold_ores")).get();
    ItemTag NON_FLAMMABLE_WOOD = getById(NamespacedKey.forMinecraft("non_flammable_wood")).get();
    ItemTag SOUL_FIRE_BASE_BLOCKS = getById(NamespacedKey.forMinecraft("soul_fire_base_blocks")).get();
    ItemTag CANDLES = getById(NamespacedKey.forMinecraft("candles")).get();
    ItemTag BOATS = getById(NamespacedKey.forMinecraft("boats")).get();
    ItemTag FISHES = getById(NamespacedKey.forMinecraft("fishes")).get();
    ItemTag SIGNS = getById(NamespacedKey.forMinecraft("signs")).get();
    ItemTag MUSIC_DISCS = getById(NamespacedKey.forMinecraft("music_discs")).get();
    ItemTag CREEPER_DROP_MUSIC_DISCS = getById(NamespacedKey.forMinecraft("creeper_drop_music_discs")).get();
    ItemTag COALS = getById(NamespacedKey.forMinecraft("coals")).get();
    ItemTag ARROWS = getById(NamespacedKey.forMinecraft("arrows")).get();
    ItemTag LECTERN_BOOKS = getById(NamespacedKey.forMinecraft("lectern_books")).get();
    ItemTag BEACON_PAYMENT_ITEMS = getById(NamespacedKey.forMinecraft("beacon_payment_items")).get();
    ItemTag STONE_TOOL_MATERIALS = getById(NamespacedKey.forMinecraft("stone_tool_materials")).get();
    ItemTag STONE_CRAFTING_MATERIALS = getById(NamespacedKey.forMinecraft("stone_crafting_materials")).get();
    ItemTag FREEZE_IMMUNE_WEARABLES = getById(NamespacedKey.forMinecraft("freeze_immune_wearables")).get();
    ItemTag AXOLOTL_TEMPT_ITEMS = getById(NamespacedKey.forMinecraft("axolotl_tempt_items")).get();
    ItemTag OCCLUDES_VIBRATION_SIGNALS = getById(NamespacedKey.forMinecraft("occludes_vibration_signals")).get();
    // endregion :: ItemTags

    static Optional<ItemTag> getById(@NotNull NamespacedKey key) {
        return Loom.getRegistry().getTag(ItemType.class, key);
    }
}
