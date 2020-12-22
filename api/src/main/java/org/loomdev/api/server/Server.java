package org.loomdev.api.server;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.ApiVersion;
import org.loomdev.api.command.CommandManager;
import org.loomdev.api.command.ConsoleCommandSource;
import org.loomdev.api.event.EventManager;
import org.loomdev.api.monitoring.TickTimes;
import org.loomdev.api.monitoring.Tps;
import org.loomdev.api.permissions.PermissionsEngine;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.scheduler.Scheduler;
import org.loomdev.api.util.registry.Registry;
import org.loomdev.api.world.WorldManager;

import java.nio.file.Path;

public interface Server {

    /**
     * Gets the name of the server implementation that is currently being run.
     *
     * @return The name of the server implementation.
     */
    @NotNull
    String getImplementationName();

    /**
     * Gets the version of the server implementation that is currently being run.
     *
     * @return The version of the server implementation.
     */
    @NotNull
    String getImplementationVersion();

    /**
     * Gets the version of Minecraft that is currently being run.
     *
     * @return The version of the Minecraft server software.
     */
    @NotNull
    String getMinecraftVersion();

    /**
     * Gets the version of the API that the server implements.
     *
     * @return The version of the API.
     */
    @NotNull
    ApiVersion getApiVersion();

    /**
     * Gets the path of the root directory container the server files.
     *
     * @return The path of the root directory.
     */
    @NotNull
    Path getRootDirectory();

    /**
     * Gets the path of the folder containing the plugin files.
     *
     * @return The path of the plugin folder.
     */
    @NotNull
    Path getPluginDirectory();

    /**
     * Gets the plugin manager.
     *
     * @return The plugin manager.
     */
    @NotNull
    PluginManager getPluginManager();

    /**
     * Gets the event manager;
     *
     * @return The event manager.
     */
    @NotNull
    EventManager getEventManager();

    /**
     * Gets the command manager.
     *
     * @return The command manager.
     */
    @NotNull
    CommandManager getCommandManager();

    /**
     * Gets the world manager.
     *
     * @return The world manager.
     */
    @NotNull
    WorldManager getWorldManager();

    /**
     * Gets the player manager.
     *
     * @return The player manager.
     */
    @NotNull
    PlayerManager getPlayerManager();

    /**
     * Gets the permissions engine.
     *
     * @return The permission engine.
     */
    PermissionsEngine getPermissionsEngine();

    /**
     * Gets the scheduler.
     *
     * @return The scheduler.
     */
    @NotNull
    Scheduler getScheduler();

    /**
     * Gets the tps of the server.
     *
     * @return Instance of {@link Tps} containing TPS measurements of the server.
     */
    @NotNull
    Tps getTps();

    /**
     * Gets the tick times of the server.
     *
     * @return Instance of {@link TickTimes} containing tick times measurements of the server.
     */
    @NotNull
    TickTimes getTickTimes();

    int getProtocolVersion();

    int getViewDistance();

    /**
     * Gets the registry.
     *
     * @return The registry.
     */
    @NotNull
    Registry getRegistry();

    /**
     * Gets the main server thread.
     *
     * @return The server thread.
     */
    @NotNull
    Thread getServerThread();

    /**
     * Gets if the current thread is the server thread.
     *
     * @return If the current thread is equal to the server thread.
     */
    boolean isOnServerThread();

    @NotNull
    ConsoleCommandSource getSource();
}
