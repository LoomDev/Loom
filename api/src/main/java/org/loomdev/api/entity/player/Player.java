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

    /**
     * Gets the player's walking/land speed (default: {@code 0.1f}).
     *
     * @return The walking speed.
     */
    float getWalkSpeed();

    /**
     * Sets the player's walking/land speed (default: {@code 0.1f}).
     *
     * @param speed The walking speed.
     */
    void setWalkSpeed(float speed);

    /**
     * Gets the player's flying speed (default: {@code 0.05f}).
     *
     * @return The flying speed.
     */
    float getFlySpeed();

    /**
     * Sets the player's flying speed (default: {@code 0.05f}).
     *
     * @param speed The flying speed.
     */
    void setFlySpeed(float speed);

    /**
     * Gets the player's view distance.
     *
     * @return The player's view distance, or if it has not been sent, the server's view distance.
     */
    int getViewDistance();

    void updateInventory();

    /**
     * Sends an action bar to the player.
     * Action bars are similar to titles, but they appear above the hotbar.
     *
     * @param message The message of the action bar.
     */
    void sendActionBar(@NotNull String message);

    /**
     * Sends an action bar to the player.
     * Action bars are similar to titles, but they appear above the hotbar.
     *
     * @param message The message of the action bar.
     */
    void sendActionBar(@NotNull Component message);

    /**
     * Sends a title (with a subtitle below) to the player.
     *
     * @param title The title.
     * @param subtitle The subtitle.
     */
    void sendTitle(@NotNull String title, @NotNull String subtitle);

    /**
     * Sends a title (with a subtitle below) to the player.
     *
     * @param title The title.
     * @param subtitle The subtitle.
     */
    void sendTitle(@NotNull Component title, @NotNull Component subtitle);

    /**
     * Sends a title (with a subtitle below) to the player.
     *
     * @param title The title.
     * @param subtitle The subtitle.
     * @param fadeIn Number of seconds to fade in.
     * @param stay Number of seconds to stay on the screen after fading in.
     * @param fadeOut Number of seconds to fade out.
     */
    void sendTitle(@NotNull String title, @NotNull String subtitle, int fadeIn, int stay, int fadeOut);

    /**
     * Sends a title (with a subtitle below) to the player.
     *
     * @param title The title.
     * @param subtitle The subtitle.
     * @param fadeIn Number of seconds to fade in.
     * @param stay Number of seconds to stay on the screen after fading in.
     * @param fadeOut Number of seconds to fade out.
     */
    void sendTitle(@NotNull Component title, @NotNull Component subtitle, int fadeIn, int stay, int fadeOut);

    @NotNull
    Optional<InetSocketAddress> getRemoteAddress();

    /**
     * Gets the player's protocol version.
     *
     * @return The protocol version (most likely the same as the server's).
     */
    int getProtocolVersion();

    /**
     * Gets the player's name that appears on the player list.
     *
     * @return The name.
     */
    @NotNull
    Optional<Component> getPlayerListName();

    /**
     * Sets the player's name that appears on the player list.
     *
     * @param name The name.
     */
    void setPlayerListName(@NotNull Component name);

    @NotNull
    Optional<Component> getPlayerListHeader();

    void setPlayerListHeader(@NotNull Component header);

    @NotNull
    Optional<Component> getPlayerListFooter();

    void setPlayerListFooter(@NotNull Component footer);

    /**
     * Plays a {@link Sound} to the player.
     *
     * @param sound The sound.
     */
    void playSound(@NotNull Sound sound);

    /**
     * Gets the current {@link GameMode} of the player.
     *
     * @return The {@link GameMode} of the player.
     */
    @NotNull GameMode getGameMode();

    /**
     * Changes the {@link GameMode} of the player.
     *
     * @param gameMode The new {@link GameMode}.
     */
    void setGameMode(@NotNull GameMode gameMode);
}
