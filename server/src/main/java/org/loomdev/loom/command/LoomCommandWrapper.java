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
import net.minecraft.commands.CommandSourceStack;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.CommandSource;
import org.loomdev.loom.server.ServerImpl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

public class LoomCommandWrapper implements com.mojang.brigadier.Command<CommandSourceStack>, Predicate<CommandSourceStack>, SuggestionProvider<CommandSourceStack> {

    private final ServerImpl server;
    private final CommandManagerImpl commandManager;
    private final CommandDispatcher<CommandSourceStack> dispatcher;

    public LoomCommandWrapper(@NotNull ServerImpl server, @NotNull CommandManagerImpl commandManager, @NotNull CommandDispatcher<CommandSourceStack> dispatcher) {
        this.server = server;
        this.commandManager = commandManager;
        this.dispatcher = dispatcher;
    }

    public LiteralCommandNode<CommandSourceStack> registerCommand(String name) {
        return this.dispatcher.register(LiteralArgumentBuilder.<CommandSourceStack>literal(name).requires(this).executes(this)
                .then(RequiredArgumentBuilder.<CommandSourceStack, String>argument("args",
                        StringArgumentType.greedyString()).suggests(this).executes(this))
        );
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) {
        return commandManager.handle(getSource(context), context.getInput());
    }

    @Override
    public boolean test(CommandSourceStack serverCommandSource) {
        return true; // TODO check for permissions in command manager
    }

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) throws CommandSyntaxException {
        List<String> suggestions = commandManager.suggest(getSource(context), builder.getInput()); // TODO async suggestions
        builder = builder.createOffset(builder.getInput().lastIndexOf(' ') + 1);
        suggestions.forEach(builder::suggest);
        return builder.buildFuture();
    }

    private @NotNull CommandSource getSource(@NotNull CommandContext<CommandSourceStack> context) {
        if (context.getSource().getEntity() != null) {
            return context.getSource().getEntity().getLoomEntity();
        }

        return server.getConsoleSource();
    }
}
