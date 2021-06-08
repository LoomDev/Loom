package org.loomdev.api.command;

import java.util.Collection;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.block.BlockState;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.util.NamespacedKey;
import org.loomdev.api.util.builder.BuilderBase;
import org.loomdev.api.util.registry.Registry;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;

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

        public Builder required();

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

        // TODO add selector api
        // Builder ofSelector();

        /**
         * Sets the argument to a {@link Location}.
         * @return The builder.
         */
        Builder ofLocation();

        /**
         * Sets the argument to a {@link BlockState}.
         * @return The builder.
         */
        Builder ofBlockState();

        /**
         * Sets the argument to a {@link ItemStack}.
         * @return The builder.
         */
        Builder ofItemStack();

        /**
         * Sets the argument to a {@link EntityType}.
         * @return The builder.
         */
        Builder ofEntityType();

        /**
         * Sets the argument to a {@link NamespacedKey}.
         * @return The builder.
         */
        Builder ofNamespacedKey();

        /**
         * Sets the argument to a {@link World}.
         * @return The builder.
         */
        Builder ofWorld();

        /**
         * Sets the suggester (completer) for the command.
         * @param suggester The suggester.
         * @return The builder.
         */
        Builder suggester(Suggester suggester);

        /**
         * Sets the suggester (completer) for the command.
         * @param suggester The suggester (as a list).
         * @return The builder.
         */
        default Builder suggester(Collection<Suggestion> suggester) {
            return suggester((ctx) -> suggester);
        }

    }

}
