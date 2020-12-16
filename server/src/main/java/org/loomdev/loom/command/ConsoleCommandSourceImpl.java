package org.loomdev.loom.command;

import net.minecraft.commands.CommandSource;
import org.loomdev.api.command.ConsoleCommandSource;

public class ConsoleCommandSourceImpl extends CommandSourceImpl implements ConsoleCommandSource {

    public ConsoleCommandSourceImpl(CommandSource stack) {
        super(stack);
    }
}
