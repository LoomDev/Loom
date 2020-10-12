package org.loomdev.api.entity.decoration;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.util.registry.Keyed;

/**
 * Represents a painting entity.
 */
public interface Painting extends DecorationEntity {

    /**
     * Get the current {@link Motive} of the painting.
     *
     * @return The current {@link Motive}.
     */
    @NotNull Motive getMotive();

    /**
     * Set the {@link Motive} of the painting.
     *
     * @param motive The new {@link Motive}.
     */
    void setMotive(@NotNull Motive motive);

    /**
     * Represents a motive of a painting.
     */
    interface Motive extends Keyed {

        Motive KEBAB = getById("minecraft:kebab");
        Motive AZTEC = getById("minecraft:aztec");
        Motive ALBAN = getById("minecraft:alban");
        Motive AZTEC2 = getById("minecraft:aztec2");
        Motive BOMB = getById("minecraft:bomb");
        Motive PLANT = getById("minecraft:plant");
        Motive WASTELAND = getById("minecraft:wasteland");
        Motive POOL = getById("minecraft:pool");
        Motive COURBET = getById("minecraft:courbet");
        Motive SEA = getById("minecraft:sea");
        Motive SUNSET = getById("minecraft:sunset");
        Motive CREEBET = getById("minecraft:creebet");
        Motive WANDERER = getById("minecraft:wanderer");
        Motive GRAHAM = getById("minecraft:graham");
        Motive MATCH = getById("minecraft:match");
        Motive BUST = getById("minecraft:bust");
        Motive STAGE = getById("minecraft:stage");
        Motive VOID = getById("minecraft:void");
        Motive SKULL_AND_ROSES = getById("minecraft:skull_and_roses");
        Motive WITHER = getById("minecraft:wither");
        Motive FIGHTERS = getById("minecraft:fighters");
        Motive POINTER = getById("minecraft:pointer");
        Motive PIGSCENE = getById("minecraft:pigscene");
        Motive BURNING_SKULL = getById("minecraft:burning_skull");
        Motive SKELETON = getById("minecraft:skeleton");
        Motive DONKEY_KONG = getById("minecraft:donkey_kong");

        /**
         * Get a motive based on the id.
         * @param id The id of the motive to get.
         * @return The motive if found, otherwise null.
         */
        static Motive getById(String id) {
            return Loom.getRegistry().getWrapped(Motive.class, id);
        }

        /**
         * Get the width in pixels.
         * <p>16 pixels per block</p>
         *
         * @return The width in pixels.
         */
        int getWith();

        /**
         * Get the height in pixels.
         * <p>16 pixels per block</p>
         *
         * @return The height in pixels.
         */
        int getHeight();

        /**
         * Get the width in blocks.
         *
         * @return The width in blocks.
         */
        default int getBlockWith() {
            return getWith() / 16;
        }

        /**
         * Get the height in blocks.
         *
         * @return The height in blocks.
         */
        default int getBlockHeight() {
            return getHeight() / 16;
        }
    }
}
