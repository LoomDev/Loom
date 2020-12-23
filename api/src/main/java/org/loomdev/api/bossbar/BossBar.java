package org.loomdev.api.bossbar;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.util.builder.BuilderBase;

import java.util.Collection;
import java.util.UUID;

/**
 * Represents a boss bar.
 * Boss bars are usually used to visually represent boss health, but can also be used to show any percentage.
 */
public interface BossBar {

    static Builder builder() {
        return Loom.getRegistry().createBuilder(BossBar.class);
    }

    @NotNull UUID getUUID();

    @NotNull Component getText();

    void setText(@NotNull String text);

    void setText(@NotNull Component text);

    float getPercent();

    void setPercent(float percent);

    @NotNull Color getColor();

    void setColor(@NotNull Color color);

    @NotNull Style getStyle();

    void setStyle(@NotNull Style style);

    boolean isVisible();

    void setVisible(boolean visible);

    boolean isDarkenScreen();

    void setDarkenScreen(boolean darkenScreen);

    boolean isThickenFog();

    void setThickenFog(boolean thickenFog);

    boolean hasDragonMusic();

    void setDragonMusic(boolean music);

    /**
     * Gets a list of players viewing the boss bar.
     *
     * @return The list.
     */
    @NotNull Collection<? extends Player> getPlayers();

    /**
     * Adds a player to the boss bar causing it (and all future changes) to be visible to them.
     *
     * @param player The player.
     */
    void addPlayer(@NotNull Player player);

    /**
     * Removes a player from the boss bar causing it to dissappear from their screen.
     *
     * @param player The player.
     */
    void removePlayer(@NotNull Player player);

    /**
     * Removes all players from the boss bar causing it to dissappear from their screens.
     */
    void removeAll();

    enum Color {
        PINK,
        BLUE,
        RED,
        GREEN,
        YELLOW,
        PURPLE,
        WHITE
    }

    enum Style {
        PROGRESS,
        NOTCHED_6,
        NOTCHED_10,
        NOTCHED_12,
        NOTCHED_20
    }

    interface Builder extends BuilderBase<BossBar, Builder> {

        Builder text(@NotNull String text);

        Builder text(@NotNull Component text);

        Builder percent(float percent);

        Builder color(@NotNull Color color);

        Builder style(@NotNull Style style);

        Builder visible(boolean visible);

        Builder darkenScreen(boolean darkenScreen);

        Builder thickenFog(boolean thickenFog);

        Builder dragonMusic(boolean dragonMusic);

        /**
         * Adds a player to the boss bar causing it (and all future changes) to be visible to them (when created).
         *
         * @param player The player.
         * @return The same builder.
         */
        Builder addPlayer(@NotNull Player... player);

        /**
         * Removes a player from the boss bar causing it not to show on their screen (when created).
         *
         * @param player The player.
         * @return The same builder.
         */
        Builder removePlayer(@NotNull Player... player);

        /**
         * Removes all players from the boss bar causing it not to show on their screens (when created).
         *
         * @return The same builder.
         */
        Builder clearPlayers();
    }
}
