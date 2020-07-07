package org.loomdev.loom.server;

import com.google.gson.Gson;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.event.EventManager;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.server.Server;
import org.loomdev.api.monitoring.TickTimes;
import org.loomdev.api.monitoring.Tps;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.event.EventManagerImpl;
import org.loomdev.loom.plugin.PluginManagerImpl;
import org.loomdev.loom.util.LoomTps;
import org.loomdev.loom.util.LoomTickTimes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;

public class LoomServer implements Server {

    private static final Logger LOGGER = LogManager.getLogger(LoomServer.class);
    public static final Gson GSON = GsonComponentSerializer.gson().serializer();

    private final MinecraftServer minecraftServer;
    private final File pluginDirectory;
    private final PluginManagerImpl pluginManager;
    private final EventManagerImpl eventManager;

    private final LoomTps tps;
    private final LoomTickTimes tickTimes;

    public LoomServer(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
        this.pluginDirectory = (File) this.minecraftServer.optionSet.valueOf("plugins");
        this.pluginManager = new PluginManagerImpl(this, this.getPluginDirectory());
        this.eventManager = new EventManagerImpl(this.pluginManager);

        this.tps = new LoomTps();
        this.tickTimes = new LoomTickTimes();

        init(); // TODO move to the appropriate place in nms.
    }

    private void init() {
        // Create required files and directories
        if (!this.pluginDirectory.exists()) {
            this.pluginDirectory.mkdirs();
        }

        // Load plugins
        try {
            this.pluginManager.loadPlugins();
        } catch (IOException e) {
            LOGGER.error("Unable to load plugins. Failed to create a directory stream.", e);
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
    public @NonNull Collection<? extends Player> getOnlinePlayers() {
        return this.minecraftServer.getPlayerManager()
                .getPlayerList()
                .stream()
                .map(PlayerEntity::getLoomEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void broadcastMessage(@NonNull Component component) {
        this.getOnlinePlayers().forEach(player -> player.sendMessage(component));
    }

    @Override
    public @NonNull Tps getTps() {
        return this.tps;
    }

    @Override
    public @NonNull TickTimes getTickTimes() {
        return this.tickTimes;
    }
}
