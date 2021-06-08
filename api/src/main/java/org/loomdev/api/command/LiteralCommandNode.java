package org.loomdev.api.command;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.builder.BuilderBase;
import org.loomdev.api.util.registry.Registry;

public interface LiteralCommandNode extends CommandNode {

    static Builder builder() {
        return Registry.get().createBuilder(LiteralCommandNode.class);
    }

    static Builder builder(@NotNull String literal) {
        return builder().literal(literal);
    }
    
    @NotNull String getLiteral();

    interface Builder extends CommandNode.Builder<LiteralCommandNode, Builder>, BuilderBase<LiteralCommandNode, Builder> {

        Builder literal(@NotNull String literal);

    }
    
}
