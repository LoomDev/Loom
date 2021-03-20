package org.loomdev.loom.server;

import joptsimple.OptionSet;
import net.minecraft.SharedConstants;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.ApiVersion;
import org.loomdev.api.command.ConsoleCommandSource;
import org.loomdev.api.monitoring.TickTimes;
import org.loomdev.api.monitoring.Tps;
import org.loomdev.api.util.registry.Registry;
import org.loomdev.api.world.WorldManager;
import org.loomdev.loom.command.CommandManagerImpl;
import org.loomdev.loom.command.ConsoleCommandSourceImpl;
import org.loomdev.loom.event.EventManagerImpl;
import org.loomdev.loom.monitoring.LoomTickTimes;
import org.loomdev.loom.monitoring.LoomTps;
import org.loomdev.loom.permissions.PermissionsEngineImpl;
import org.loomdev.loom.plugin.InternalPluginManager;
import org.loomdev.loom.scheduler.SchedulerImpl;
import org.loomdev.loom.util.registry.RegistryImpl;
import org.loomdev.loom.world.WorldManagerImpl;

import java.io.File;
import java.nio.file.Path;

public class ServerImpl {

    private static ServerImpl instance;

    public static ServerImpl getInstance() {
        return instance;
    }

    private final MinecraftServer minecraftServer;
    private final File pluginDirectory;
    private final InternalPluginManager pluginManager;
    private final EventManagerImpl eventManager;
    private final CommandManagerImpl commandManager;
    private final SchedulerImpl scheduler;
    private final WorldManagerImpl worldManager;
    private final PlayerManagerImpl playerManager;
    private final PermissionsEngineImpl permissionsEngine;

    private final LoomTps tps;
    private final LoomTickTimes tickTimes;
    private final RegistryImpl registry;
    private final ConsoleCommandSource commandSource;

    public ServerImpl(MinecraftServer minecraftServer, OptionSet optionSet) {
        instance = this;

        this.minecraftServer = minecraftServer;
        this.pluginDirectory = (File) optionSet.valueOf("plugins");
        this.pluginManager = new InternalPluginManager(this, pluginDirectory.toPath());
        this.eventManager = new EventManagerImpl();
        this.commandManager = new CommandManagerImpl(this);
        this.scheduler = new SchedulerImpl();
        this.worldManager = new WorldManagerImpl();
        this.playerManager = new PlayerManagerImpl(this);
        this.permissionsEngine = new PermissionsEngineImpl(this);

        this.tps = new LoomTps();
        this.tickTimes = new LoomTickTimes();
        this.registry = new RegistryImpl();
        this.commandSource = new ConsoleCommandSourceImpl(minecraftServer);
    }

    public void shutdown() {
        pluginManager.disableOnServerStop();
        scheduler.shutdown();
    }

    @NotNull
    public String getImplementationName() {
        return "Loom";
    }

    @NotNull
    public String getImplementationVersion() {
        var version = ServerImpl.class.getPackage().getImplementationVersion();
        return version == null ? "DEVELOPMENT" : version;
    }

    @NotNull
    public String getMinecraftVersion() {
        return minecraftServer.getServerVersion();
    }

    @NotNull
    public ApiVersion getApiVersion() {
        return ApiVersion.LATEST;
    }

    @NotNull
    public Path getRootDirectory() {
        return minecraftServer.getServerDirectory().toPath();
    }

    @NotNull
    public Path getPluginDirectory() {
        return pluginDirectory.toPath();
    }

    @NotNull
    public EventManagerImpl getEventManager() {
        return eventManager;
    }

    @NotNull
    public CommandManagerImpl getCommandManager() {
        return commandManager;
    }

    @NotNull
    public WorldManager getWorldManager() {
        return worldManager;
    }

    @NotNull
    public PlayerManagerImpl getPlayerManager() {
        return playerManager;
    }

    public PermissionsEngineImpl getPermissionsEngine() {
        return this.permissionsEngine;
    }

    @NotNull
    public SchedulerImpl getScheduler() {
        return scheduler;
    }

    @NotNull
    public Tps getTps() {
        return this.tps;
    }

    @NotNull
    public TickTimes getTickTimes() {
        return tickTimes;
    }

    public int getProtocolVersion() {
        return SharedConstants.getCurrentVersion().getProtocolVersion();
    }

    public int getViewDistance() {
        return minecraftServer.getPlayerList().getViewDistance();
    }

    @NotNull
    public Registry getRegistry() {
        return registry;
    }

    @NotNull
    public Thread getServerThread() {
        return minecraftServer.getRunningThread();
    }

    public boolean isOnServerThread() {
        return Thread.currentThread() == getServerThread();
    }

    @NotNull
    public ConsoleCommandSource getSource() {
        return commandSource;
    }

    public InternalPluginManager getPluginManager() {
        return this.pluginManager;
    }

    public MinecraftServer getMinecraftServer() {
        return minecraftServer;
    }

    public void halt() {
        getMinecraftServer().halt(false);
    }
}
