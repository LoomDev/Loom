package org.loomdev.api.server;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.ApiVersion;
import org.loomdev.api.command.CommandManager;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.EventManager;
import org.loomdev.api.monitoring.TickTimes;
import org.loomdev.api.monitoring.Tps;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.scheduler.Scheduler;
import org.loomdev.api.util.registry.Registry;
import org.loomdev.api.world.World;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface Server {

    /**
     * Get the name of the server implementation that is currently being ran.
     *
     * @return The name of the server implementation.
     */
    @NotNull String getName();

    /**
     * Get the version of the server implementation that is currently being ran.
     *
     * @return The version of the server implementation.
     */
    @NotNull String getVersion();

    /**
     * Get the version of Minecraft that is currently being ran.
     *
     * @return The version of the Minecraft server software.
     */
    @NotNull String getMinecraftVersion();

    /**
     * Get the version of the api that the server implements.
     *
     * @return The version of the api.
     */
    @NotNull ApiVersion getApiVersion();

    /**
     * Get the path of the root directory container the server files.
     *
     * @return The path of the root directory.
     */
    @NotNull Path getRootDirectory();

    /**
     * Get the path of the folder containing the plugin files.
     *
     * @return The path of the plugin folder.
     */
    @NotNull Path getPluginDirectory();

    /**
     * Get the plugin manager.
     *
     * @return The plugin manager.
     */
    @NotNull PluginManager getPluginManager();

    /**
     * Get the event manager;
     *
     * @return The event manager.
     */
    @NotNull EventManager getEventManager();

    /**
     * Get the command manager.
     *
     * @return The command manager.
     */
    @NotNull CommandManager getCommandManager();

    /**
     * Get the scheduler.
     *
     * @return The scheduler.
     */
    @NotNull Scheduler getScheduler();

    /**
     * Returns all currently online players on the server.
     *
     * @return Immutable collection of all online players.
     */
    @NotNull Collection<? extends Player> getOnlinePlayers();

    /**
     * Send a message to all players on the server.
     *
     * @param message The message to send.
     */
    default void broadcastMessage(@NotNull String message) {
        broadcastMessage(Component.text(message));
    }

    /**
     * Send a message to all players on the server.
     *
     * @param component The component to send.
     */
    void broadcastMessage(@NotNull Component component);

    /**
     * Returns an instance of the console command source.
     *
     * @return The console command source for this server.
     */
    @NotNull CommandSource getConsoleSource();

    /**
     * Get the tps of the server.
     *
     * @return Instance of {@link Tps} containing TPS measurements of the server.
     */
    @NotNull Tps getTps();

    /**
     * Get the tick times of the server.
     *
     * @return Instance of {@link TickTimes} containing tick times measurements of the server.
     */
    @NotNull TickTimes getTickTimes();

    @NotNull Collection<World> getWorlds();

    @NotNull Optional<World> getWorld(@NotNull String name);

    @NotNull Optional<World> getWorld(@NotNull UUID uuid);

    int getProtocolVersion();

    int getViewDistance();

    @NotNull Registry getRegistry();
}
