package org.loomdev.loom.command;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
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
import java.util.concurrent.CompletableFuture;

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
    }

    @NotNull
    private CommandDispatcher<CommandSourceStack> getDispatcher() {
        return server.getMinecraftServer().getCommands().getDispatcher();
    }

    @NotNull
    private CommandSource getSource(@NotNull CommandSourceStack stack) {
        var source = stack.source;

        if (stack.getEntity() != null) {
            return stack.getEntity().getLoomEntity();
        }

        if (source instanceof MinecraftServer) {
            return new ConsoleCommandSourceImpl(source);
        }

        return new CommandSourceImpl(source);
    }

    @Override
    public void register(@NotNull Object plugin, @NotNull Command command) {
        server.getPluginManager().fromInstance(plugin)
                .ifPresent(container -> register(container.getMetadata(), command));
    }

    @Override
    public void register(@NotNull PluginMetadata metadata, @NotNull Command command) {
        String commandName = command.getName().toLowerCase(Locale.ENGLISH).trim();

        if (!StringUtils.isAlphanumeric(commandName)) {
            return;
        }

        var pluginId = metadata.getId();
        registerAlias(metadata.getId(), command, commandName);
        registerAlias(metadata.getId(), command, pluginId + ":" + commandName);

        for (var alias : command.getAliases()) {
            if (!StringUtils.isAlphanumeric(commandName)) {
                return;
            }

            if (commands.containsKey(alias)) {
                return;
            }

            registerAlias(metadata.getId(), command, alias);
            registerAlias(metadata.getId(), command, pluginId + ":" + alias);
        }
    }

    private void registerAlias(@NotNull String pluginId, @NotNull Command command, @NotNull String alias) {
        commands.put(alias, command);
        pluginCommands.put(pluginId, alias);
        internalRegister(alias, command);
    }

    private void internalRegister(@NotNull String alias, @NotNull Command command) {
        var arguments = RequiredArgumentBuilder.<CommandSourceStack, String>argument("arguments", StringArgumentType.greedyString())
                .suggests(this::suggest)
                .executes(this::execute);

        getDispatcher().register(LiteralArgumentBuilder.<CommandSourceStack>literal(alias)
                .requires(commandSourceStack -> {
                    var commandSource = getSource(commandSourceStack);
                    return command.getPermission()
                            .map(commandSource::hasPermission)
                            .orElse(true);
                })
                .executes(this::execute)
                .then(arguments));
    }

    @Override
    public void unregister(@NotNull Object plugin) {
        server.getPluginManager().fromInstance(plugin).ifPresent(container -> unregister(container.getMetadata()));
    }

    @Override
    public void unregister(@NotNull PluginMetadata metadata) {
        Collection<String> commands = pluginCommands.get(metadata.getId());
        if (commands != null) {
            commands.forEach(this.commands::remove);
        }
        pluginCommands.removeAll(metadata.getId());
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        var arguments = context.getInput().split(" ");
        if (arguments.length == 0) {
            return 0;
        }

        var alias = arguments[0].toLowerCase(Locale.ENGLISH);
        alias = alias.startsWith("/") ? alias.substring(1) : alias;

        var command = commands.get(alias);
        if (command == null) {
            return 0;
        }

        try {
            var input = Arrays.copyOfRange(arguments, 1, arguments.length);
            command.execute(new CommandContextImpl(getSource(context.getSource()), alias, input));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return 1;
    }

    @NotNull
    private CompletableFuture<Suggestions> suggest(CommandContext<CommandSourceStack> context, SuggestionsBuilder suggestionsBuilder) {
        var builder = suggestionsBuilder.createOffset(suggestionsBuilder.getInput().lastIndexOf(' ') + 1);
        return CompletableFuture.supplyAsync(() -> {
            var arguments = builder.getInput().split(" ");
            if (arguments.length == 0) {
                return builder.build();
            }

            var alias = arguments[0].toLowerCase(Locale.ENGLISH);
            alias = alias.startsWith("/") ? alias.substring(1) : alias;

            var command = commands.get(alias);
            if (command == null) {
                return builder.build();
            }

            var source = getSource(context.getSource());
            var input = Arrays.copyOfRange(arguments, 1, arguments.length);
            for (var suggestion : command.suggest(new CommandContextImpl(source, alias, input))) {
                builder.suggest(suggestion);
            }

            return builder.build();
        });
    }

    public void internalReload() {
        commands.forEach(this::internalRegister);
    }
}
