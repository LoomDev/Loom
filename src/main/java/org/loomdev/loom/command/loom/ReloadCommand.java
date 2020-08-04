package org.loomdev.loom.command.loom;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandSource;

public class ReloadCommand extends Command {

    public ReloadCommand() {
        super("reload", "rl");
        setDescription("Reloads the server configuration and all plugins.");
        setUsage("/reload");
        setPermission("loom.command.reload");
    }

    @Override
    public void execute(@NonNull CommandSource source, String[] args) {
        // TODO minecraft reload and config stuff.
    }
}
