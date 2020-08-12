package org.loomdev.loom.command.loom;

import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.server.Server;

public class VersionCommand extends Command {

    private final Server server;

    public VersionCommand(Server server) {
        super("version", "about", "loom");
        setDescription("Displays version information of the server software.");
        setUsage("/about");

        this.server = server;
    }

    @Override
    public void execute(@NotNull CommandSource commandSource, String[] strings) {
        commandSource.sendMessage(TextComponent.of(String.format(
                "This server is running %s %s (MC: %s).",
                server.getName(), server.getVersion(), server.getMinecraftVersion()
        )));
    }
}
