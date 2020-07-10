package org.loomdev.loom.command.loom;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandSource;

public class DebugCommand extends Command {

    public DebugCommand() {
        super("debug");
    }

    @Override
    public void execute(@NonNull CommandSource commandSource, String[] strings) {
        // Do debug shit
    }
}
