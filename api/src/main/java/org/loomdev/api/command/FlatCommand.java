package org.loomdev.api.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.tree.CommandNode;
import org.loomdev.api.command.tree.LiteralCommandNode;
import org.loomdev.api.command.tree.argument.ArgumentCommandNode;
import org.loomdev.api.command.tree.argument.CustomArgumentType;
import org.loomdev.api.command.tree.argument.Suggestions;

/**
 * Represents a flat command, which is a simplified command, without {@link CommandNode}s.
 */
public interface FlatCommand {

    String getLiteral();

    int execute(CommandSource source, Collection<String> args);

    default CompletableFuture<Suggestions> suggest(CommandSource source, Suggestions.Builder builder) {
        return Suggestions.empty();
    }

    /**
     * Convert the basic command into a {@link CommandNode}.
     * @return The command node.
     */
    default CommandNode convert() {
        return LiteralCommandNode.builder(getLiteral())
                       .then(ArgumentCommandNode.builder()
                               .id("arguments")
                               .of(new FlatArgument(this))
                               .executesVoid((ctx) -> Arrays.asList(ctx.getValue("arguments", String.class).split(" "))))
                       .build();
    }
    
    class FlatArgument implements CustomArgumentType<String> {

        private FlatCommand command;
        
        public FlatArgument(FlatCommand command) {
            this.command = command;
        }

        @Override
        public String read(CommandReader reader) {
            String text = reader.getRemaining();
            reader.setCursor(reader.getTotalLength());
            return text;
        }

        @Override
        @NotNull
        public CompletableFuture<Suggestions> suggest(CommandContext ctx, Suggestions.Builder builder) {
            return command.suggest(ctx.getSource(), builder);
        }
        
    }
    
}
