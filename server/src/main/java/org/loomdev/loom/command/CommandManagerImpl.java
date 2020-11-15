package org.loomdev.loom.command;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandManager;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.plugin.PluginMetadata;
import org.loomdev.loom.Loom;
import org.loomdev.loom.command.loom.PluginsCommand;
import org.loomdev.loom.command.loom.TpsCommand;
import org.loomdev.loom.command.loom.VersionCommand;
import org.loomdev.loom.server.ServerImpl;

import java.util.*;

public class CommandManagerImpl implements CommandManager {

    private final ServerImpl server;
    private final Map<String, Command> commands;
    private final Multimap<String, String> pluginCommands;

    public CommandManagerImpl(ServerImpl server) {
        this.server = server;
        this.commands = new HashMap<>();
        this.pluginCommands = ArrayListMultimap.create();

        register(Loom.LOOM_PLUGIN, new PluginsCommand());
        register(Loom.LOOM_PLUGIN, new TpsCommand(server));
        register(Loom.LOOM_PLUGIN, new VersionCommand(server));
        internalReload();
    }

    @Override
    public void register(@NotNull Object plugin, @NotNull Command command) {
        var container = server.getPluginManager().fromInstance(plugin);
        if (container != null) {
            register(container.getMetadata(), command);
        }
    }

    @Override
    public void register(@NotNull PluginMetadata metadata, @NotNull Command command) {
        String commandName = command.getName().toLowerCase(Locale.ENGLISH).trim();

        if (!StringUtils.isAlphanumeric(commandName)) {
            return;
        }

        String namespacedName = metadata.getId() + ":" + commandName;
        commands.put(commandName, command);
        commands.put(namespacedName, command);
        pluginCommands.put(metadata.getId(), commandName);
        pluginCommands.put(metadata.getId(), namespacedName);

        Arrays.stream(command.getAliases())
                .filter(StringUtils::isAlphanumeric)
                .filter(alias -> !commands.containsKey(alias))
                .forEach(alias -> {
                    String namespacedAlias = metadata.getId() + ":" + alias;
                    commands.put(alias, command);
                    commands.put(namespacedAlias, command);
                    pluginCommands.put(metadata.getId(), alias);
                    pluginCommands.put(metadata.getId(), namespacedAlias);
                });
    }

    @Override
    public void unregister(@NotNull Object plugin) {
        var container = server.getPluginManager().fromInstance(plugin);
        if (container == null) return;

        Collection<String> commands = pluginCommands.get(container.getMetadata().getId());
        if (commands != null) {
            commands.forEach(this.commands::remove);
        }
        pluginCommands.removeAll(container.getMetadata().getId());
    }

    @Override
    public void unregister(@NotNull PluginMetadata metadata) {
        // TODO
    }

    public void internalReload() {
        commands.forEach((name, command) -> {
            var wrapper = new LoomCommandWrapper(server, this, server.getMinecraftServer().getCommands().getDispatcher())
                    .registerCommand(name);
        });

        /*commands.forEach((name, command) -> this.server.getMinecraftServer().getCommandManager().getDispatcher()
                .register(LiteralArgumentBuilder.<ServerCommandSource>literal(name)
                        .requires(source -> true) // TODO test permission
                        .executes(context -> handle(getSource(context), context.getInput()))
                        .then(RequiredArgumentBuilder.<ServerCommandSource, String>argument("args",
                                StringArgumentType.greedyString()).suggests(provider -> command.suggest(provider)).executes(null)) // TODO suggestions
                ));*/
    }

    private @NotNull CommandSource getSource(@NotNull CommandContext<CommandSourceStack> context) {
        if (context.getSource().getEntity() != null) {
            return context.getSource().getEntity().getLoomEntity();
        }

        return server.getConsoleSource();
    }

    public int handle(@NotNull CommandSource source, @NotNull String input) {
        String[] args = input.split("\\s+");

        if (args.length == 0) {
            return 0;
        }

        String alias = args[0].toLowerCase(Locale.ENGLISH);
        alias = alias.startsWith("/") ? alias.substring(1) : alias;
        Command command = commands.get(alias);

        if (command == null) {
            return 0;
        }

        try {
            command.execute(new CommandContextImpl(source, alias, Arrays.copyOfRange(args, 1, args.length)));
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
