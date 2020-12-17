package org.loomdev.api.entity.player;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.util.GameMode;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.Weather;

import java.net.InetSocketAddress;
import java.util.Optional;

public interface Player extends LivingEntity {

    boolean isConnected();

    boolean isCrouching();

    void setCrouching(boolean crouching);

    boolean isSprinting();

    void setSprinting(boolean sprinting);

    float getWalkSpeed();

    void setWalkSpeed(float speed);

    float getFlySpeed();

    void setFlySpeed(float speed);

    int getViewDistance();

    void updateInventory();

    void sendActionbar(@NotNull String message);

    void sendActionbar(@NotNull Component message);

    void sendTitle(@NotNull String top, @NotNull String bottom);

    void sendTitle(@NotNull Component top, @NotNull Component bottom);

    void sendTitle(@NotNull String top, @NotNull String bottom, int fadeIn, int stay, int fadeOut);

    void sendTitle(@NotNull Component top, @NotNull Component bottom, int fadeIn, int stay, int fadeOut);

    @Nullable
    InetSocketAddress getRemoteAddress();

    int getProtocolVersion();

    @Nullable
    Component getTabListName();

    void setTabListName(@NotNull Component text);

    @Nullable
    Component getTabListHeader();

    void setTabListHeader(@NotNull Component text);

    @Nullable
    Component getTabListFooter();

    void setTabListFooter(@NotNull Component text);

    void playSound(@NotNull Sound sound);

    /**
     * Get the current {@link GameMode} of the player.
     *
     * @return The {@link GameMode} of the player.
     */
    @NotNull GameMode getGameMode();

    /**
     * Change the {@link GameMode} of the player.
     *
     * @param gameMode The new {@link GameMode}.
     */
    void setGameMode(@NotNull GameMode gameMode);
}
