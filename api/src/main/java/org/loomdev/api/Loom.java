package org.loomdev.api;

import org.loomdev.api.command.CommandManager;
import org.loomdev.api.event.EventManager;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.scheduler.Scheduler;
import org.loomdev.api.server.Server;
import org.loomdev.api.util.registry.Registry;

public abstract class Loom {

    private static Server server;

    public static void setServer(Server instance) {
        if (server != null) {
            throw new UnsupportedOperationException("The server instance cannot be modified after it has been set.");
        }

        Loom.server = instance;
    }

    public static Server getServer() {
        return Loom.server;
    }

    public static PluginManager getPluginManager() {
        return getServer().getPluginManager();
    }

    public static EventManager getEventManager() {
        return getServer().getEventManager();
    }

    public static CommandManager getCommandManager() {
        return getServer().getCommandManager();
    }

    public static Scheduler getScheduler() {
        return getServer().getScheduler();
    }

    public static Registry getRegistry() {
        return getServer().getRegistry();
    }
}
