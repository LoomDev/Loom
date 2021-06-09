package org.loomdev.api.command;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.tree.argument.ArgumentCommandNode;

/**
 * Represents the context in which a command is executed.
 * Contains the {@link CommandSource}.
 */
public interface CommandContext {

    @NotNull CommandSource getSource();

    /**
     * Gets the value of an argument.
     * @param <V> The argument type.
     * @param arg The argument identifier.
     * @param type The type of argument.
     * @return The value.
     * @see ArgumentCommandNode.Builder#id(String)
     */
    @Nullable
    <V> V getValue(String arg, Class<V> type);

    /**
     * Gets the value of an argument.
     * @param <V> The argument type.
     * @param arg The argument identifier.
     * @return The value.
     * @see ArgumentCommandNode.Builder#id(String)
     */
    default <V> V getValue(String arg) {
        return (V) getValue(arg, Object.class);
    }

    /**
     * Gets whether the command has a value.
     * @param arg The argument identifier.
     * @return <code>true</code> if the command has a value.
     */
    default boolean hasValue(String arg) {
        return getValue(arg, Object.class) != null;
    }

}
