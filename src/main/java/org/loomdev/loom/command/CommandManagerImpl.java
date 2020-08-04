package org.loomdev.loom.command;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandManager;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.plugin.Plugin;
import org.loomdev.api.plugin.PluginMetadata;
import org.loomdev.loom.Loom;
import org.loomdev.loom.command.loom.DebugCommand;
import org.loomdev.loom.command.loom.PluginsCommand;
import org.loomdev.loom.command.loom.TpsCommand;
import org.loomdev.loom.command.loom.VersionCommand;
import org.loomdev.loom.server.ServerImpl;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class CommandManagerImpl implements CommandManager {

    private final ServerImpl server;
    private final MinecraftServer minecraftServer;
    private final LoomCommandWrapper wrapper;

    private final Map<String, Command> commands = new HashMap<>();
    private final Multimap<String, String> commandsByPlugin = ArrayListMultimap.create();

    public CommandManagerImpl(ServerImpl server, MinecraftServer minecraftServer) {
        this.server = server;
        this.minecraftServer = minecraftServer;
        this.wrapper = new LoomCommandWrapper(server, this, minecraftServer.serverResourceManager.commandManager.getDispatcher());

        register(new DebugCommand());
        register(new PluginsCommand());
        register(new TpsCommand(server));
        register(new VersionCommand(server));
    }

    @Override
    public void register(@NonNull Plugin plugin, @NonNull Command command) {
        server.getPluginManager().fromInstance(plugin).ifPresent((container) -> {
            PluginMetadata metadata = container.getMetadata();
            String name = command.getName().toLowerCase(Locale.ENGLISH).trim();

            register(metadata, command, command.getName());

            // Register non-conflicting aliases
            for (String alias : command.getAliases()) {
                if (!commands.containsKey(alias)) {
                    register(metadata, command, alias);
                }
            }
        });
    }

    private void register(@NonNull Command command) {
        String name = command.getName().toLowerCase(Locale.ENGLISH).trim();

        // Register command name if no conflicts exist
        if (!commands.containsKey(name)) {
            register(Loom.LOOM_PLUGIN, command, name);
        }

        // Register non-conflicting aliases
        for (String alias : command.getAliases()) {
            if (!commands.containsKey(alias)) {
                register(Loom.LOOM_PLUGIN, command, alias);
            }
        }
    }

    private void register(@NonNull PluginMetadata metadata, @NonNull Command command, @NonNull String name) {
        if (StringUtils.isAlphanumeric(name)) {

            // Register namespaced name
            String namespacedName = String.format("%s:%s", metadata.getId(), name);
            commands.put(namespacedName, command);
            commandsByPlugin.put(metadata.getId(), namespacedName);
            this.wrapper.registerCommand(namespacedName);

            // Register normal command
            commands.put(name, command);
            commandsByPlugin.put(metadata.getId(), name);
            this.wrapper.registerCommand(name);
        }
    }

    @Override
    public void unregister(@NonNull Plugin plugin) {
        server.getPluginManager().fromInstance(plugin).ifPresent((container) -> {
            Collection<String> commands = commandsByPlugin.get(container.getMetadata().getId());

            if (commands != null) {
                commands.forEach(this.commands::remove);
            }

            commandsByPlugin.removeAll(container.getMetadata().getId());
        });
    }

    public int handle(@NonNull CommandSource source, @NonNull String input) {
        String[] args = input.split("\\s+");

        if (args.length == 0) {
            return 0;
        }

        String name = args[0].toLowerCase(Locale.ENGLISH);
        name = name.startsWith("/") ? name.substring(1) : name;
        Command command = commands.get(name);

        if (command == null) {
            return 0;
        }

        try {
            command.execute(source, name, Arrays.copyOfRange(args, 1, args.length));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return 1;
    }

    public List<String> suggest(@NotNull CommandSource source, @NotNull String input) {
        String[] args = input.split("\\s+");

        if (args.length == 0) {
            return ImmutableList.of();
        }

        String name = args[0].toLowerCase(Locale.ENGLISH);
        name = name.startsWith("/") ? name.substring(1) : name;
        Command command = commands.get(name);

        if (command == null) {
            return ImmutableList.of();
        }

        return command.suggest(source, Arrays.copyOfRange(args, 1, args.length));
    }
}
