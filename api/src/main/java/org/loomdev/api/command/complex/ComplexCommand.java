package org.loomdev.api.command.complex;

import org.loomdev.api.Loom;
import org.loomdev.api.command.CommandExecutor;
import org.loomdev.api.command.complex.arguments.Argument;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.util.builder.BuilderBase;

import java.util.function.BiFunction;

public class ComplexCommand {

    public static Builder builder() {
        return Loom.getRegistry().createBuilder(ComplexCommand.class);
    }

    interface Builder extends BuilderBase<ComplexCommand, Builder> {

        Builder description(String description);

        Builder permission(String permission);

        Builder arguments(Argument... arguments);

        Builder executor(CommandExecutor commandExecutor); // ?

        Builder executePlayer(BiFunction<Player, String[], Integer> executor); // ?

    }

}
