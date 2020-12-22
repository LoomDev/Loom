package org.loomdev.api.entity.vehicle;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.item.ItemType;

public interface Boat extends Entity {

    /**
     * Gets the {@link Variant} of the Boat.
     *
     * @return The {@link Variant} of the Boat.
     */
    @NotNull Variant getVariant();

    /**
     * Sets the {@link Variant} of the Boat.
     *
     * @param variant The new {@link Variant} of the Boat.
     */
    void setVariant(@NotNull Variant variant);

    Placement getPlacement();
    
    enum Variant {
        OAK(BlockType.OAK_PLANKS, ItemType.OAK_BOAT, "oak"),
        SPRUCE(BlockType.SPRUCE_PLANKS, ItemType.SPRUCE_BOAT, "spruce"),
        BIRCH(BlockType.BIRCH_PLANKS, ItemType.BIRCH_BOAT, "birch"),
        JUNGLE(BlockType.JUNGLE_PLANKS, ItemType.JUNGLE_BOAT, "jungle"),
        ACACIA(BlockType.ACACIA_PLANKS, ItemType.ACACIA_BOAT, "acacia"),
        DARK_OAK(BlockType.DARK_OAK_PLANKS, ItemType.DARK_OAK_BOAT, "dark_oak");

        private final BlockType plankType;
        private final ItemType itemType;
        private final String name;

        Variant(BlockType plankType, ItemType itemType, String name) {
            this.plankType = plankType;
            this.itemType = itemType;
            this.name = name;
        }

        private String getName() {
            return this.name;
        }

        public ItemType getItemType() {
            return itemType;
        }

        public BlockType getPlankType() {
            return plankType;
        }
    }

    enum Placement {
        IN_WATER,
        UNDER_WATER,
        UNDER_FLOWING_WATER,
        ON_LAND,
        IN_AIR
    }
}
