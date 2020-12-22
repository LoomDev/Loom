package org.loomdev.api;

import org.loomdev.api.command.CommandManager;
import org.loomdev.api.event.EventManager;
import org.loomdev.api.permissions.PermissionsEngine;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.scheduler.Scheduler;
import org.loomdev.api.server.PlayerManager;
import org.loomdev.api.server.Server;
import org.loomdev.api.util.registry.Registry;
import org.loomdev.api.world.WorldManager;

/**
 * Holds server instance.
 */
public abstract class Loom {

    private static Server server;

    /**
     * Sets the current server instance.
     *
     * @param instance The new instance.
     * @throws UnsupportedOperationException if the server has already been set.
     */
    public static void setServer(Server instance) {
        if (server != null) {
            throw new UnsupportedOperationException("The server instance cannot be modified after it has been set.");
        }

        Loom.server = instance;
    }

    /**
     * Gets the current server instance.
     *
     * @return The instance.
     */
    public static Server getServer() {
        return Loom.server;
    }

    /**
     * Gets the plugin manager.
     *
     * @return The plugin manager.
     */
    public static PluginManager getPluginManager() {
        return getServer().getPluginManager();
    }

    /**
     * Gets the event manager;
     *
     * @return The event manager.
     */
    public static EventManager getEventManager() {
        return getServer().getEventManager();
    }

    /**
     * Gets the command manager.
     *
     * @return The command manager.
     */
    public static CommandManager getCommandManager() {
        return getServer().getCommandManager();
    }

    /**
     * Gets the world manager.
     *
     * @return The world manager.
     */
    public static WorldManager getWorldManager() {
        return getServer().getWorldManager();
    }

    /**
     * Gets the player manager.
     *
     * @return The player manager.
     */
    public static PlayerManager getPlayerManager() {
        return getServer().getPlayerManager();
    }

    /**
     * Gets the permissions engine.
     *
     * @return The permission engine.
     */
    public static PermissionsEngine getPermissionsEngine() {
        return getServer().getPermissionsEngine();
    }

    /**
     * Gets the scheduler.
     *
     * @return The scheduler.
     */
    public static Scheduler getScheduler() {
        return getServer().getScheduler();
    }

    /**
     * Gets the registry.
     *
     * @return The registry.
     */
    public static Registry getRegistry() {
        return getServer().getRegistry();
    }
}
