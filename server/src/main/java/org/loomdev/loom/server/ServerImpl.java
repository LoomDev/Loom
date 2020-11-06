package org.loomdev.loom.server;

import com.google.gson.Gson;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.minecraft.SharedConstants;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.ApiVersion;
import org.loomdev.api.Loom;
import org.loomdev.api.command.CommandManager;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.EventManager;
import org.loomdev.api.monitoring.TickTimes;
import org.loomdev.api.monitoring.Tps;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.server.Server;
import org.loomdev.api.util.registry.Registry;
import org.loomdev.api.world.World;
import org.loomdev.loom.command.CommandManagerImpl;
import org.loomdev.loom.command.ConsoleSource;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.event.EventManagerImpl;
import org.loomdev.loom.monitoring.LoomTickTimes;
import org.loomdev.loom.monitoring.LoomTps;
import org.loomdev.loom.plugin.PluginManagerImpl;
import org.loomdev.loom.scheduler.SchedulerImpl;
import org.loomdev.loom.util.registry.RegistryImpl;
import org.loomdev.loom.world.WorldImpl;

import java.io.File;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class ServerImpl implements Server {

    private static final Logger LOGGER = LogManager.getLogger("Server");
    public static final Gson GSON = GsonComponentSerializer.gson().serializer();

    private final MinecraftServer minecraftServer;
    private final File pluginDirectory;
    private final PluginManagerImpl pluginManager;
    private final EventManagerImpl eventManager;
    private final CommandManagerImpl commandManager;
    private final SchedulerImpl scheduler;
    private final CommandSource consoleSource;
    private final LoomTps tps;
    private final LoomTickTimes tickTimes;
    private final RegistryImpl registry;

    private final Map<UUID, World> worlds = new HashMap<>();

    public ServerImpl(MinecraftServer minecraftServer) {
        Loom.setServer(this);
        this.minecraftServer = minecraftServer;
        this.pluginDirectory = (File) this.minecraftServer.optionSet.valueOf("plugins");
        this.pluginManager = new PluginManagerImpl(this, this.pluginDirectory);
        this.eventManager = new EventManagerImpl(this.pluginManager);
        this.commandManager = new CommandManagerImpl(this);
        this.consoleSource = new ConsoleSource(minecraftServer);
        this.scheduler = new SchedulerImpl(pluginManager);
        this.scheduler.start();
        this.tps = new LoomTps();
        this.tickTimes = new LoomTickTimes();
        this.registry = new RegistryImpl();

        init(); // TODO move to the appropriate place in nms.
    }

    private void init() {
        pluginManager.scanPluginDirectory();
    }

    @Override
    @NotNull
    public String getName() {
        return "Loom";
    }

    @Override
    public @NotNull String getVersion() {
        var version = ServerImpl.class.getPackage().getImplementationVersion();
        return version == null ? "DEVELOPMENT" : version;
    }

    @Override
    @NotNull
    public String getMinecraftVersion() {
        return minecraftServer.getServerVersion();
    }

    @Override
    @NotNull
    public ApiVersion getApiVersion() {
        return ApiVersion.LATEST;
    }

    @Override
    @NotNull
    public Path getRootDirectory() {
        return minecraftServer.getServerDirectory().toPath();
    }

    @Override
    @NotNull
    public Path getPluginDirectory() {
        return pluginDirectory.toPath();
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

    @NotNull
    @Override
    public SchedulerImpl getScheduler() {
        return scheduler;
    }

    @Override
    @NotNull
    public Collection<? extends Player> getOnlinePlayers() {
        return minecraftServer.getPlayerList()
                .getPlayers()
                .stream()
                .map(e -> (PlayerImpl) e.getLoomEntity())
                .collect(Collectors.toList());
    }

    @Override
    public void broadcastMessage(@NotNull String message) {
        this.broadcastMessage(TextComponent.of(message));
    }

    @Override
    public void broadcastMessage(@NotNull Component component) {
        getOnlinePlayers().forEach(player -> player.sendMessage(component));
    }

    @Override
    @NotNull
    public CommandSource getConsoleSource() {
        return consoleSource;
    }

    @Override
    @NotNull
    public Tps getTps() {
        return this.tps;
    }

    @Override
    @NotNull
    public TickTimes getTickTimes() {
        return tickTimes;
    }

    public void registerWorld(@NotNull ServerLevel minecraftWorld) {
        /*if (this.worlds.containsKey(world.getUUID())) {
            throw new IllegalStateException(String.format("World '%s' is a duplicate of an already loaded world.", world.getName()));
        }*/

        World world = new WorldImpl(minecraftWorld);
        worlds.put(world.getUUID(), world);
    }

    @Override
    @NotNull
    public Collection<World> getWorlds() {
        return worlds.values();
    }

    @Override
    @Nullable
    public World getWorld(@NotNull String name) {
        return worlds.values().stream()
                .filter(world -> world.getName().equals(name))
                .findFirst().orElse(null);
    }

    @Override
    @Nullable
    public World getWorld(@NotNull UUID uuid) {
        return worlds.get(uuid);
    }

    @Override
    public int getProtocolVersion() {
        return SharedConstants.getCurrentVersion().getProtocolVersion();
    }

    @Override
    public int getViewDistance() {
        return minecraftServer.getPlayerList().getViewDistance();
    }

    @Override
    @NotNull
    public Registry getRegistry() {
        return registry;
    }

    public MinecraftServer getMinecraftServer() {
        return minecraftServer;
    }
}
