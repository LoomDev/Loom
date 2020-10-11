package org.loomdev.api.entity.decoration;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
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

        Motive KEBAB = Loom.getRegistry().getWrapped(Motive.class, "minecraft:kebab");
        Motive AZTEC = Loom.getRegistry().getWrapped(Motive.class, "minecraft:aztec");
        Motive ALBAN = Loom.getRegistry().getWrapped(Motive.class, "minecraft:alban");
        Motive AZTEC2 = Loom.getRegistry().getWrapped(Motive.class, "minecraft:aztec2");
        Motive BOMB = Loom.getRegistry().getWrapped(Motive.class, "minecraft:bomb");
        Motive PLANT = Loom.getRegistry().getWrapped(Motive.class, "minecraft:plant");
        Motive WASTELAND = Loom.getRegistry().getWrapped(Motive.class, "minecraft:wasteland");
        Motive POOL = Loom.getRegistry().getWrapped(Motive.class, "minecraft:pool");
        Motive COURBET = Loom.getRegistry().getWrapped(Motive.class, "minecraft:courbet");
        Motive SEA = Loom.getRegistry().getWrapped(Motive.class, "minecraft:sea");
        Motive SUNSET = Loom.getRegistry().getWrapped(Motive.class, "minecraft:sunset");
        Motive CREEBET = Loom.getRegistry().getWrapped(Motive.class, "minecraft:creebet");
        Motive WANDERER = Loom.getRegistry().getWrapped(Motive.class, "minecraft:wanderer");
        Motive GRAHAM = Loom.getRegistry().getWrapped(Motive.class, "minecraft:graham");
        Motive MATCH = Loom.getRegistry().getWrapped(Motive.class, "minecraft:match");
        Motive BUST = Loom.getRegistry().getWrapped(Motive.class, "minecraft:bust");
        Motive STAGE = Loom.getRegistry().getWrapped(Motive.class, "minecraft:stage");
        Motive VOID = Loom.getRegistry().getWrapped(Motive.class, "minecraft:void");
        Motive SKULL_AND_ROSES = Loom.getRegistry().getWrapped(Motive.class, "minecraft:skull_and_roses");
        Motive WITHER = Loom.getRegistry().getWrapped(Motive.class, "minecraft:wither");
        Motive FIGHTERS = Loom.getRegistry().getWrapped(Motive.class, "minecraft:fighters");
        Motive POINTER = Loom.getRegistry().getWrapped(Motive.class, "minecraft:pointer");
        Motive PIGSCENE = Loom.getRegistry().getWrapped(Motive.class, "minecraft:pigscene");
        Motive BURNING_SKULL = Loom.getRegistry().getWrapped(Motive.class, "minecraft:burning_skull");
        Motive SKELETON = Loom.getRegistry().getWrapped(Motive.class, "minecraft:skeleton");
        Motive DONKEY_KONG = Loom.getRegistry().getWrapped(Motive.class, "minecraft:donkey_kong");

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
