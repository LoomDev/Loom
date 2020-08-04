package org.loomdev.loom.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.server.command.ServerCommandSource;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.CommandSource;
import org.loomdev.loom.server.ServerImpl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

public class LoomCommandWrapper implements com.mojang.brigadier.Command<ServerCommandSource>, Predicate<ServerCommandSource>, SuggestionProvider<ServerCommandSource> {

    private final ServerImpl server;
    private final CommandManagerImpl commandManager;
    private final CommandDispatcher<ServerCommandSource> dispatcher;

    public LoomCommandWrapper(@NotNull ServerImpl server, @NotNull CommandManagerImpl commandManager, @NotNull CommandDispatcher<ServerCommandSource> dispatcher) {
        this.server = server;
        this.commandManager = commandManager;
        this.dispatcher = dispatcher;
    }

    public LiteralCommandNode<ServerCommandSource> registerCommand(String name) {
        return this.dispatcher.register(LiteralArgumentBuilder.<ServerCommandSource>literal(name).requires(this).executes(this)
                .then(RequiredArgumentBuilder.<ServerCommandSource, String>argument("args",
                        StringArgumentType.greedyString()).suggests(this).executes(this))
        );
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) {
        return commandManager.handle(getSource(context), context.getInput());
    }

    @Override
    public boolean test(ServerCommandSource serverCommandSource) {
        return true; // TODO check for permissions in command manager
    }

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) throws CommandSyntaxException {
        List<String> suggestions = commandManager.suggest(getSource(context), builder.getInput()); // TODO async suggestions
        builder = builder.createOffset(builder.getInput().lastIndexOf(' ') + 1);
        suggestions.forEach(builder::suggest);
        return builder.buildFuture();
    }

    private @NotNull CommandSource getSource(@NotNull CommandContext<ServerCommandSource> context) {
        if (context.getSource().getEntity() != null) {
            return context.getSource().getEntity().getLoomEntity();
        }

        return server.getConsoleSource();
    }
}
