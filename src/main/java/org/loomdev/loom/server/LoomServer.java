package org.loomdev.loom.server;

import com.google.gson.Gson;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.plugin.PluginManager;
import org.loomdev.api.server.Server;
import org.loomdev.api.monitoring.TickTimes;
import org.loomdev.api.monitoring.Tps;
import org.loomdev.loom.plugin.LoomPluginManager;
import org.loomdev.loom.util.LoomTps;
import org.loomdev.loom.util.LoomTickTimes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

public class LoomServer implements Server {

    private static final Logger LOGGER = LogManager.getLogger(LoomServer.class);
    public static final Gson GSON = GsonComponentSerializer.gson().serializer();

    private final MinecraftServer minecraftServer;
    private final File pluginDirectory;
    private final LoomPluginManager pluginManager;

    private final LoomTps tps;
    private final LoomTickTimes tickTimes;

    public LoomServer(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
        this.pluginDirectory = (File) this.minecraftServer.optionSet.valueOf("plugins");
        this.pluginManager = new LoomPluginManager(this, this.getPluginDirectory());

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
    public String getName() {
        return "Loom";
    }

    @Override
    public String getVersion() {
        return LoomServer.class.getPackage().getImplementationVersion();
    }

    @Override
    public Path getRootDirectory() {
        return minecraftServer.getRunDirectory().toPath();
    }

    @Override
    public Path getPluginDirectory() {
        return this.pluginDirectory.toPath();
    }

    @Override
    public PluginManager getPluginManager() {
        return this.pluginManager;
    }

    @Override
    public Collection<? extends Player> getOnlinePlayers() {
        return null;
    }

    @Override
    public void broadcastMessage(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Tps getTps() {
        return this.tps;
    }

    @Override
    public TickTimes getTickTimes() {
        return this.tickTimes;
    }
}
