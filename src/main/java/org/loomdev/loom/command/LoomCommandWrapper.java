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
import org.loomdev.loom.server.LoomServer;

import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

public class LoomCommandWrapper implements com.mojang.brigadier.Command<ServerCommandSource>, Predicate<ServerCommandSource>, SuggestionProvider<ServerCommandSource> {

    private final LoomServer server;
    private final CommandDispatcher<ServerCommandSource> dispatcher;

    public LoomCommandWrapper(LoomServer server, CommandDispatcher<ServerCommandSource> dispatcher) {
        this.server = server;
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
        if (context.getSource().getEntity() != null) {
            return server.getCommandManager().handle(context.getSource().getEntity().getLoomEntity(), context.getInput());
        }

        return server.getCommandManager().handle(this.server.getConsoleSource(), context.getInput());
    }

    @Override
    public boolean test(ServerCommandSource serverCommandSource) {
        return true; // TODO check for permissions in command manager
    }

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> commandContext, SuggestionsBuilder suggestionsBuilder) throws CommandSyntaxException {
        return suggestionsBuilder.buildFuture(); // TODO hook into command tab complete
    }
}
