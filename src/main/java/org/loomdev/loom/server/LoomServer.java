package org.loomdev.loom.server;

import com.google.gson.Gson;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.minecraft.SharedConstants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.Loom;
import org.loomdev.api.command.CommandManager;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.EventManager;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.server.Server;
import org.loomdev.api.monitoring.TickTimes;
import org.loomdev.api.monitoring.Tps;
import org.loomdev.api.world.World;
import org.loomdev.loom.command.CommandManagerImpl;
import org.loomdev.loom.command.ConsoleSource;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.event.EventManagerImpl;
import org.loomdev.loom.plugin.PluginManagerImpl;
import org.loomdev.loom.monitoring.LoomTps;
import org.loomdev.loom.monitoring.LoomTickTimes;
import org.loomdev.loom.world.WorldImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class LoomServer implements Server {

    private static final Logger LOGGER = LogManager.getLogger(LoomServer.class);
    public static final Gson GSON = GsonComponentSerializer.gson().serializer();

    private final MinecraftServer minecraftServer;
    private final File pluginDirectory;
    private final PluginManagerImpl pluginManager;
    private final EventManagerImpl eventManager;
    private final CommandManagerImpl commandManager;
    private final CommandSource consoleSource;

    private final LoomTps tps;
    private final LoomTickTimes tickTimes;

    private final Map<String, World> worlds = new LinkedHashMap<>();

    public LoomServer(MinecraftServer minecraftServer) {
        Loom.setServer(this);
        this.minecraftServer = minecraftServer;
        this.pluginDirectory = (File) this.minecraftServer.optionSet.valueOf("plugins");
        this.pluginManager = new PluginManagerImpl(this, this.pluginDirectory);
        this.eventManager = new EventManagerImpl(this.pluginManager);
        this.commandManager = new CommandManagerImpl(this, minecraftServer);
        this.consoleSource = new ConsoleSource(minecraftServer);

        this.tps = new LoomTps();
        this.tickTimes = new LoomTickTimes();

        init(); // TODO move to the appropriate place in nms.
    }

    private void init() {
        // Load plugins
        try {
            this.pluginManager.loadPlugins();
        } catch (IOException e) {
            LOGGER.error("Failed to load plugins- unable to create a directory stream.", e);
        }
    }

    @Override
    public @NonNull String getName() {
        return "Loom";
    }

    @Override
    public @NonNull String getVersion() {
        return LoomServer.class.getPackage().getImplementationVersion();
    }

    @Override
    public @NonNull String getMinecraftVersion() {
        return minecraftServer.getVersion();
    }

    @Override
    public @NonNull Path getRootDirectory() {
        return minecraftServer.getRunDirectory().toPath();
    }

    @Override
    public @NonNull Path getPluginDirectory() {
        return this.pluginDirectory.toPath();
    }

    @Override
    public @NonNull PluginManager getPluginManager() {
        return this.pluginManager;
    }

    @Override
    public @NonNull EventManager getEventManager() {
        return this.eventManager;
    }

    @Override
    public @NonNull CommandManager getCommandManager() {
        return this.commandManager;
    }

    @Override
    public @NonNull Collection<? extends Player> getOnlinePlayers() {
        return this.minecraftServer.getPlayerManager()
                .getPlayerList()
                .stream()
                .map(e -> (PlayerImpl) e.getLoomEntity())
                .collect(Collectors.toList());
    }

    @Override
    public void broadcastMessage(@NonNull String message) {
        this.broadcastMessage(TextComponent.of(message));
    }

    @Override
    public void broadcastMessage(@NonNull Component component) {
        this.getOnlinePlayers().forEach(player -> player.sendMessage(component));
    }

    @Override
    public @NonNull CommandSource getConsoleSource() {
        return this.consoleSource;
    }

    @Override
    public @NonNull Tps getTps() {
        return this.tps;
    }

    @Override
    public @NonNull TickTimes getTickTimes() {
        return this.tickTimes;
    }

    @Override
    public @NonNull Optional<World> getWorld(@NonNull String name) {
        for (ServerWorld world : this.minecraftServer.getWorlds()) {
            if (world.field_24456.getLevelName().equals(name)) {
                return Optional.of(WorldImpl.of(world));
            }
        }
        
        return Optional.empty();
        //return Optional.ofNullable(this.worlds.get(name));
    }

    @Override
    public @NonNull Optional<World> getWorld(@NonNull UUID uuid) {
        return this.worlds.values().stream()
                .filter(world -> world.getUUID().equals(uuid))
                .findFirst();
    }

    @Override
    public int getProtocolVersion() {
        return SharedConstants.getGameVersion().getProtocolVersion();
    }

    @Override
    public int getViewDistance() {
        return this.minecraftServer.getPlayerManager().getViewDistance();
    }
}
