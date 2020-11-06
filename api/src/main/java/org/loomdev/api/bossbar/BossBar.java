package org.loomdev.api.bossbar;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.util.builder.BuilderBase;

import java.util.Collection;
import java.util.UUID;

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

    @NotNull Collection<? extends Player> getPlayers();

    void addPlayer(@NotNull Player player);

    void removePlayer(@NotNull Player player);

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

        Builder addPlayer(@NotNull Player... player);

        Builder removePlayer(@NotNull Player... player);

        Builder clearPlayers();
    }
}
