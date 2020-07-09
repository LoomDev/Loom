package org.loomdev.loom.command;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandManager;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.plugin.PluginContainer;
import org.loomdev.api.plugin.PluginMetadata;
import org.loomdev.loom.command.loom.TpsCommand;
import org.loomdev.loom.command.loom.VersionCommand;
import org.loomdev.loom.server.LoomServer;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommandManagerImpl implements CommandManager {

    private final LoomServer server;
    private final CommandDispatcher<ServerCommandSource> dispatcher;

    private final Map<String, Command> registeredCommands = new HashMap<>();
    private final Multimap<String, String> pluginCommands = ArrayListMultimap.create();

    public CommandManagerImpl(LoomServer server, MinecraftServer minecraftServer) {
        this.server = server;
        this.dispatcher = minecraftServer.serverResourceManager.commandManager.getDispatcher();

        // Register Loom built-in command
        register(new TpsCommand(server, minecraftServer));
        register(new VersionCommand(server));
    }

    @Override
    public synchronized void register(@NonNull Plugin plugin, @NotNull Command command) {
        server.getPluginManager().fromInstance(plugin).ifPresent((container) -> {
            PluginMetadata metadata = container.getMetadata();
            String name = command.getName().toLowerCase(Locale.ENGLISH).trim();

            // Register command name if no conflicts exist
            if (!registeredCommands.containsKey(name)) {
                register(metadata, command, command.getName());
            }

            // Register non-conflicting aliases
            for (String alias : command.getAliases()) {
                if (!registeredCommands.containsKey(alias)) {
                    register(metadata, command, alias);
                }
            }
        });
    }

    private synchronized void register(@NotNull Command command) {
        String name = command.getName().toLowerCase(Locale.ENGLISH).trim();

        // Register command name if no conflicts exist
        if (!registeredCommands.containsKey(name)) {
            register(DUMMY_METADATA, command, command.getName());
        }

        // Register non-conflicting aliases
        for (String alias : command.getAliases()) {
            if (!registeredCommands.containsKey(alias)) {
                register(DUMMY_METADATA, command, alias);
            }
        }
    }

    private void register(@NonNull PluginMetadata metadata, @NonNull Command command, @NonNull String name) {
        if (StringUtils.isAlphanumeric(name)) {

            // Register namespaced name
            registeredCommands.put(String.format("%s:%s", metadata.getId(), name), command);

            // Register normal command
            registeredCommands.put(name, command);
            pluginCommands.put(metadata.getId(), name);
        }
    }

    @Override
    public int handle(@NotNull CommandSource source, @NotNull String input) {
        String[] args = input.split("\\s+");

        if (args.length == 0) {
            return 0;
        }

        Command command = registeredCommands.get(args[0].toLowerCase(Locale.ENGLISH).substring(1));

        if (command == null) {
            return 0;
        }

        command.execute(source, args);
        return 1;
    }

    @Override
    public void updateCommandMap() {
        LoomCommandWrapper wrapper = new LoomCommandWrapper(this.server, this.dispatcher);
        registeredCommands.forEach((name, command) -> wrapper.registerCommand(name));
    }

    // TODO methods for enabling/disabling commands based on plugins

    private static final PluginMetadata DUMMY_METADATA = new PluginMetadata() {

        @Override
        public @NonNull String getId() {
            return "loom";
        }
    };
}
