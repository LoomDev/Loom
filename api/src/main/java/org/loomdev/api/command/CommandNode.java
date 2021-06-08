package org.loomdev.api.command;

import java.util.Collection;
import java.util.function.Predicate;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.permissions.PermissionSubject;

/**
 * Represents part of a command.
 */
public interface CommandNode {

    @NotNull CommandExecutor getExecutor();

    /**
     * Gets all aliases of the command added with {@code Builder#alias(String)}.
     * @return The aliases.
     */
    @NotNull Collection<String> getAliases();
    
    @NotNull Collection<CommandNode> getChildren();

    /**
     * Adds a child node.
     * @param node The node.
     */
    void addChild(@NotNull CommandNode node);

    /**
     * Removes a child node.
     * @param node The node.
     */
    void removeChild(@NotNull CommandNode node);

    interface Builder<N extends CommandNode, B extends Builder<N, B>> {

        /**
         * Sets the id of the argument.
         * Used in {@link CommandContext#getValue(String)}
         * @param id The id.
         * @return The builder.
         */
        B id(@NotNull String id);

        /**
         * Adds a child node.
         * @param node The node
         * @see {@link CommandNode#addChild(CommandNode)}
         * @return The builder.
         */
        B then(@NotNull CommandNode node);

        /**
         * Adds a child node.
         * @param node The node builder.
         * @see {@link CommandNode#addChild(CommandNode)}
         * @return The builder.
         */
        B then(@NotNull Builder<?, ?> node);

        /**
         * Sets the command's executor.
         * The return value is an integer representing the result of the command.
         * @param executor The executor.
         * @return The builder.
         */
        B executesInteger(@NotNull CommandExecutor executor);

        /**
         * Sets the command's executor.
         * @param executor The executor.
         * @return The builder.
         */
        B executesBoolean(@NotNull CommandExecutor.BooleanCommandExecutor executor);

        /**
         * Sets the command's executor.
         * @param executor The executor.
         * @return The builder.
         */
        B executesVoid(@NotNull CommandExecutor.VoidCommandExecutor executor);

        /**
         * Redirects the node to another one.
         * @param node The node.
         * @return The builder.
         */
        B redirects(@NotNull CommandNode node);

        /**
         * Redirects the node to another one.
         * @param node The node.
         * @return The builder.
         */
        B redirects(@NotNull Builder<?, ?> node);

        /**
         * Adds a requirement to the node.
         * @param predicate The requirement.
         * @return The builder.
         */
        B requires(Predicate<CommandContext> predicate);

        /**
         * Creates an command redirecting to this.
         * @param alias The command name.
         * @return The builder.
         */
        B alias(String alias);

        /**
         * Creates commands redirecting to this.
         * @param alias The command names.
         * @return The builder.
         */
        default B aliases(String... aliases) {
            for (String alias : aliases) {
                alias(alias);
            }
            return (B) this;
        }

        /**
         * Adds a permission requirement to the node.
         * @param permission The permission.
         * @see {@link PermissionSubject#hasPermission(String)}
         * @return The builder.
         */
        default B requiresPermission(String permission) {
            return requires((ctx) -> ctx.getSource().hasPermission(permission));
        }

        /**
         * Adds a operator requirement to the node.
         * @see {@link #requires(Predicate)}
         * @see {@link PermissionSubject#isOperator()}
         * @return The builder.
         */
        default B requiresOperator() {
            return requires((ctx) -> ctx.getSource().isOperator());
        }

    }
}
