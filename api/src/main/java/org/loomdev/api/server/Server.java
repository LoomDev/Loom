package org.loomdev.api.server;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.ApiVersion;
import org.loomdev.api.command.CommandManager;
import org.loomdev.api.command.ConsoleCommandSource;
import org.loomdev.api.event.EventManager;
import org.loomdev.api.monitoring.TickTimes;
import org.loomdev.api.monitoring.Tps;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.scheduler.Scheduler;
import org.loomdev.api.util.registry.Registry;
import org.loomdev.api.world.WorldManager;

import java.nio.file.Path;

public interface Server {

    /**
     * Get the name of the server implementation that is currently being ran.
     *
     * @return The name of the server implementation.
     */
    @NotNull
    String getImplementationName();

    /**
     * Get the version of the server implementation that is currently being ran.
     *
     * @return The version of the server implementation.
     */
    @NotNull
    String getImplementationVersion();

    /**
     * Get the version of Minecraft that is currently being ran.
     *
     * @return The version of the Minecraft server software.
     */
    @NotNull
    String getMinecraftVersion();

    /**
     * Get the version of the api that the server implements.
     *
     * @return The version of the api.
     */
    @NotNull
    ApiVersion getApiVersion();

    /**
     * Get the path of the root directory container the server files.
     *
     * @return The path of the root directory.
     */
    @NotNull
    Path getRootDirectory();

    /**
     * Get the path of the folder containing the plugin files.
     *
     * @return The path of the plugin folder.
     */
    @NotNull
    Path getPluginDirectory();

    /**
     * Get the plugin manager.
     *
     * @return The plugin manager.
     */
    @NotNull
    PluginManager getPluginManager();

    /**
     * Get the event manager;
     *
     * @return The event manager.
     */
    @NotNull
    EventManager getEventManager();

    /**
     * Get the command manager.
     *
     * @return The command manager.
     */
    @NotNull
    CommandManager getCommandManager();

    /**
     * Get the world manager.
     *
     * @return The world manager.
     */
    @NotNull
    WorldManager getWorldManager();

    /**
     * Get the player manager.
     *
     * @return The player manager.
     */
    @NotNull
    PlayerManager getPlayerManager();

    /**
     * Get the scheduler.
     *
     * @return The scheduler.
     */
    @NotNull
    Scheduler getScheduler();

    /**
     * Get the tps of the server.
     *
     * @return Instance of {@link Tps} containing TPS measurements of the server.
     */
    @NotNull
    Tps getTps();

    /**
     * Get the tick times of the server.
     *
     * @return Instance of {@link TickTimes} containing tick times measurements of the server.
     */
    @NotNull
    TickTimes getTickTimes();

    int getProtocolVersion();

    int getViewDistance();

    @NotNull
    Registry getRegistry();

    @NotNull
    Thread getServerThread();

    boolean isOnServerThread();

    @NotNull
    ConsoleCommandSource getSource();
}
