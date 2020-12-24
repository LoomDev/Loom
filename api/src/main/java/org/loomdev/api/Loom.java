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
 * Holds the current {@link Server} instance.
 */
public abstract class Loom {

    private static Server server;

    /**
     * Sets the current server instance.
     * <p>This method should only be used by API implementations.</p>
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
     * @see Server#getPluginManager()
     */
    public static PluginManager getPluginManager() {
        return getServer().getPluginManager();
    }

    /**
     * Gets the event manager;
     *
     * @return The event manager.
     * @see Server#getEventManager()
     */
    public static EventManager getEventManager() {
        return getServer().getEventManager();
    }

    /**
     * Gets the command manager.
     *
     * @return The command manager.
     * @see Server#getCommandManager()
     */
    public static CommandManager getCommandManager() {
        return getServer().getCommandManager();
    }

    /**
     * Gets the world manager.
     *
     * @return The world manager.
     * @see Server#getWorldManager()
     */
    public static WorldManager getWorldManager() {
        return getServer().getWorldManager();
    }

    /**
     * Gets the player manager.
     *
     * @return The player manager.
     * @see Server#getPlayerManager()
     */
    public static PlayerManager getPlayerManager() {
        return getServer().getPlayerManager();
    }

    /**
     * Gets the permissions engine.
     *
     * @return The permission engine.
     * @see Server#getPermissionsEngine()
     */
    public static PermissionsEngine getPermissionsEngine() {
        return getServer().getPermissionsEngine();
    }

    /**
     * Gets the scheduler.
     *
     * @return The scheduler.
     * @see Server#getScheduler()
     */
    public static Scheduler getScheduler() {
        return getServer().getScheduler();
    }

    /**
     * Gets the registry.
     *
     * @return The registry.
     * @see Server#getRegistry()
     */
    public static Registry getRegistry() {
        return getServer().getRegistry();
    }
}
