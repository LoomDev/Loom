package org.loomdev.api.command.tree.argument;


import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.command.CommandReader;
import org.loomdev.api.command.CommandSyntaxExeception;
import org.loomdev.api.command.tree.CommandNode;
import org.loomdev.api.command.tree.CommandNode.Builder;
import org.loomdev.api.util.InvalidNamespacedKeyException;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.util.builder.BuilderBase;
import org.loomdev.api.util.registry.Registry;

public interface ArgumentCommandNode extends CommandNode {

    static Builder builder() {
        return Registry.get().createBuilder(ArgumentCommandNode.class);
    }

    static Builder builder(String id) {
        return builder().id(id);
    }

    @NotNull String getName();

    public interface Builder
            extends CommandNode.Builder<ArgumentCommandNode, Builder>, BuilderBase<ArgumentCommandNode, Builder> {

        Builder required();

        Builder ofBoolean();

        default Builder ofDouble() {
            return ofDouble(Double.MIN_VALUE, Double.MAX_VALUE);
        }

        Builder ofDouble(double min, double max);

        default Builder ofFloat() {
            return ofFloat(Float.MIN_VALUE, Float.MAX_VALUE);
        }

        Builder ofFloat(float min, float max);

        default Builder ofInteger() {
            return ofInteger(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        Builder ofInteger(int min, int max);

        /**
         * Sets the argument to a {@link String}.
         * @see {@link #ofString(StringType)}
         * @return The builder.
         */
        default Builder ofString() {
            return ofString(StringType.SINGLE_WORD);
        }

        /**
         * Sets the argument to a {@link String}.
         * @param type The method of reading the string.
         * @return The builder.
         */
        Builder ofString(StringType type);

        /**
         * Sets the argument to a plugin ID.
         * @return The builder.
         */
        Builder ofPlugin();

        /**
         * Sets the argument to a {@link NamespacedKey}.
         * @return The builder.
         */
        default Builder ofNamespacedKey() {
            return of(new CustomArgumentType<NamespacedKey>() {
                
                @Override
                public NamespacedKey read(CommandReader reader) {
                    String id = reader.getRead();
                    try {
                        return NamespacedKey.of(id);
                    } catch (InvalidNamespacedKeyException error) {
                        throw CommandSyntaxExeception.create(error.getMessage(), reader.getString(), reader.getCursor());
                    }
                }
                
            });
        }

        /**
         * Sets the argument to a custom one.
         * @param type The argument type.
         * @return The builder.
         */
        Builder of(CustomArgumentType<?> type);

    }

}
