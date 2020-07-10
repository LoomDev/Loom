package org.loomdev.loom.command;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandManager;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.plugin.PluginMetadata;
import org.loomdev.loom.command.loom.PluginsCommand;
import org.loomdev.loom.command.loom.TpsCommand;
import org.loomdev.loom.command.loom.VersionCommand;
import org.loomdev.loom.plugin.PluginManagerImpl;
import org.loomdev.loom.server.LoomServer;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommandManagerImpl implements CommandManager {

    private final LoomServer server;
    private final LoomCommandWrapper wrapper;

    private final Map<String, Command> registeredCommands = new HashMap<>();
    private final Multimap<String, String> pluginCommands = ArrayListMultimap.create();

    public CommandManagerImpl(LoomServer server, MinecraftServer minecraftServer) {
        this.server = server;
        this.wrapper = new LoomCommandWrapper(server, minecraftServer.serverResourceManager.commandManager.getDispatcher());

        register(new PluginsCommand());
        register(new TpsCommand(server));
        register(new VersionCommand(server));
    }

    @Override
    public void register(@NonNull Plugin plugin, @NonNull Command command) {
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

    private void register(@NonNull Command command) {
        String name = command.getName().toLowerCase(Locale.ENGLISH).trim();

        // Register command name if no conflicts exist
        if (!registeredCommands.containsKey(name)) {
            register(PluginManagerImpl.DUMMY_METADATA, command, command.getName());
        }

        // Register non-conflicting aliases
        for (String alias : command.getAliases()) {
            if (!registeredCommands.containsKey(alias)) {
                register(PluginManagerImpl.DUMMY_METADATA, command, alias);
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
    public int handle(@NonNull CommandSource source, @NonNull String input) {
        String[] args = input.split("\\s+"); // TODO remove command from arg, aka first index

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

    public void updateCommandMap() {
        registeredCommands.forEach((name, command) -> this.wrapper.registerCommand(name));
    }

    // TODO methods for enabling/disabling commands based on plugins
}
