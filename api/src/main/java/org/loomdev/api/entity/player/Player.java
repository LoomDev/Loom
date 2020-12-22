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

    /**
     * Gets if the player is connected to the server.
     *
     * @return If the player is connected.
     */
    boolean isConnected();

    /**
     * Gets if the player is crouching.
     *
     * @return If the player is crouching.
     */
    boolean isCrouching();

    /**
     * Forces if the player is crouching.
     *
     * @param crouching If the player is crouching.
     */
    void setCrouching(boolean crouching);

    /**
     * Gets if the player is sprinting.
     *
     * @return If the player is sprinting.
     */
    boolean isSprinting();

    /**
     * Forces if the player is sprinting.
     *
     * @param sprinting If the player is sprinting.
     */
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
     * @return The player's view distance, or if it has not been sent the server's view distance.
     */
    int getViewDistance();

    /**
     * Updates the player's inventory.
     */
    void updateInventory();

    /**
     * Sends an action bar to the player that will appear above the hotbar.
     *
     * @param message The message of the action bar.
     */
    void sendActionbar(@NotNull String message);

    /**
     * Sends an action bar to the player that will appear above the hotbar.
     *
     * @param message The message of the action bar.
     */
    void sendActionbar(@NotNull Component message);

    /**
     * Sends a title (with a subtitle below it) to the player.
     *
     * @param title The title.
     * @param subtitle The subtitle.
     */
    void sendTitle(@NotNull String title, @NotNull String subtitle);

    /**
     * Sends a title (with a subtitle below it) to the player.
     *
     * @param title The title.
     * @param subtitle The subtitle.
     */
    void sendTitle(@NotNull Component title, @NotNull Component subtitle);

    /**
     * Sends a title (with a subtitle below it) to the player.
     *
     * @param title The title.
     * @param subtitle The subtitle.
     * @param fadeIn Number of seconds to fade in.
     * @param stay Number of seconds to stay on the screen after fading in.
     * @param fadeOut Number of seconds to fade out.
     */
    void sendTitle(@NotNull String title, @NotNull String subtitle, int fadeIn, int stay, int fadeOut);

    /**
     * Sends a title (with a subtitle below it) to the player.
     *
     * @param title The title.
     * @param subtitle The subtitle.
     * @param fadeIn Number of seconds to fade in.
     * @param stay Number of seconds to stay on the screen after fading in.
     * @param fadeOut Number of seconds to fade out.
     */
    void sendTitle(@NotNull Component title, @NotNull Component subtitle, int fadeIn, int stay, int fadeOut);

    /**
     * Gets the player's address.
     *
     * @return The address.
     */
    @Nullable
    InetSocketAddress getRemoteAddress();

    /**
     * Gets the player's protocol version.
     *
     * @return The protocol version (most likely the same as the server's).
     */
    int getProtocolVersion();

    /**
     * Gets the player's name that appears on the tab list.
     *
     * @return The name.
     */
    @Nullable
    Component getTabListName();

    /**
     * Sets the player's name that appears on the tab list.
     *
     * @param name The name.
     */
    void setTabListName(@NotNull Component name);

    /**
     * Gets the text that appears at the top of the tab list.
     *
     * @return The text.
     */
    @Nullable
    Component getTabListHeader();

    /**
     * Sets the text that appears at the top of the tab list.
     *
     * @param text The text.
     */
    void setTabListHeader(@NotNull Component text);

    /**
     * Gets the text that appears at the bottom of the tab list.
     *
     * @return The text.
     */
    @Nullable
    Component getTabListFooter();

    /**
     * Sets the text that appears at the bottom of the tab list.
     *
     * @param text The text.
     */
    void setTabListFooter(@NotNull Component text);

    /**
     * Plays a sound to the player.
     *
     * @param sound The sound.
     */
    void playSound(@NotNull Sound sound);

    /**
     * Gets the current game mode of the player.
     *
     * @return The game mode of the player.
     */
    @NotNull GameMode getGameMode();

    /**
     * Changes the game mode of the player.
     *
     * @param gameMode The new game mode.
     */
    void setGameMode(@NotNull GameMode gameMode);
}
