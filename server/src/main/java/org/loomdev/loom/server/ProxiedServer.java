package org.loomdev.loom.server;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.ApiVersion;
import org.loomdev.api.command.CommandManager;
import org.loomdev.api.command.ConsoleCommandSource;
import org.loomdev.api.event.EventManager;
import org.loomdev.api.monitoring.TickTimes;
import org.loomdev.api.monitoring.Tps;
import org.loomdev.api.permissions.PermissionsEngine;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.plugin.metadata.PluginMetadata;
import org.loomdev.api.scheduler.Scheduler;
import org.loomdev.api.server.PlayerManager;
import org.loomdev.api.server.Server;
import org.loomdev.api.util.registry.Registry;
import org.loomdev.api.world.WorldManager;
import org.loomdev.loom.command.ProxiedCommandManager;
import org.loomdev.loom.event.ProxiedEventManager;
import org.loomdev.loom.permissions.ProxiedPermissionsEngine;
import org.loomdev.loom.plugin.ProxiedPluginManager;

import java.nio.file.Path;

public class ProxiedServer implements Server {

    private final ServerImpl internalServer;

    private final ProxiedPluginManager pluginManager;
    private final ProxiedEventManager eventManager;
    private final ProxiedCommandManager commandManager;
    private final ProxiedPermissionsEngine permissionsEngine;

    public ProxiedServer(PluginMetadata metadata, @NotNull ServerImpl internalServer) { // @NotNull
        this.internalServer = internalServer;

        pluginManager = new ProxiedPluginManager(metadata, internalServer);
        eventManager = new ProxiedEventManager(metadata, internalServer);
        commandManager = new ProxiedCommandManager(metadata, internalServer);
        permissionsEngine = new ProxiedPermissionsEngine(metadata, internalServer);
    }

    @Override
    @NotNull
    public String getImplementationName() {
        return internalServer.getImplementationName();
    }

    @Override
    @NotNull
    public String getImplementationVersion() {
        return internalServer.getImplementationVersion();
    }

    @Override
    @NotNull
    public String getMinecraftVersion() {
        return internalServer.getMinecraftVersion();
    }

    @Override
    @NotNull
    public ApiVersion getApiVersion() {
        return internalServer.getApiVersion();
    }

    @Override
    @NotNull
    public Path getRootDirectory() {
        return internalServer.getRootDirectory();
    }

    @Override
    @NotNull
    public Path getPluginDirectory() {
        return internalServer.getPluginDirectory();
    }

    @Override
    @NotNull
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    @NotNull
    public EventManager getEventManager() {
        return eventManager;
    }

    @Override
    @NotNull
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    @NotNull
    public WorldManager getWorldManager() {
        return internalServer.getWorldManager();
    }

    @Override
    @NotNull
    public PlayerManager getPlayerManager() {
        return internalServer.getPlayerManager();
    }

    @Override
    public PermissionsEngine getPermissionsEngine() {
        return permissionsEngine;
    }

    @Override
    @NotNull
    public Scheduler getScheduler() {
        return internalServer.getScheduler();
    }

    @Override
    @NotNull
    public Tps getTps() {
        return internalServer.getTps();
    }

    @Override
    @NotNull
    public TickTimes getTickTimes() {
        return internalServer.getTickTimes();
    }

    @Override
    public int getProtocolVersion() {
        return internalServer.getProtocolVersion();
    }

    @Override
    public int getViewDistance() {
        return internalServer.getViewDistance();
    }

    @Override
    @NotNull
    public Thread getServerThread() {
        return internalServer.getServerThread();
    }

    @Override
    public boolean isOnServerThread() {
        return internalServer.isOnServerThread();
    }

    @Override
    @NotNull
    public ConsoleCommandSource getSource() {
        return internalServer.getSource();
    }
}
