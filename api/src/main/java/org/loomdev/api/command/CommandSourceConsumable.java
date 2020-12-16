package org.loomdev.api.command;

import org.loomdev.api.entity.player.Player;

import java.util.function.Consumer;

public interface CommandSourceConsumable {

    boolean isPlayer();

    boolean isConsole();

    CommandSourceConsumable ifPlayer(Consumer<Player> consumer);

    CommandSourceConsumable ifConsole(Consumer<ConsoleCommandSource> consumer);

    void orElse(Consumer<CommandSource> consumer);
}
