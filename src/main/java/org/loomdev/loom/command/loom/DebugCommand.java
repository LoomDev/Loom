package org.loomdev.loom.command.loom;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.Loom;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandSource;

public class DebugCommand extends Command {

    public DebugCommand() {
        super("debug");
    }

    @Override
    public void execute(@NonNull CommandSource commandSource, String[] strings) {
        // Do debug shit

        Loom.getServer().getOnlinePlayers().forEach(player -> {
            if (player.isConnected()) {
                player.kick(TextComponent.of("you're a stupid cunt").color(TextColor.of(241, 140, 255)));
            }
        });

        /*Loom.getServer().getWorld("world").ifPresent(world -> {
            world.playSound(((PlayerImpl) commandSource).getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1.0f);
            commandSource.sendMessage("Played sound.");

            world.displayParticle(((PlayerImpl) commandSource).getLocation(), ParticleEffect.HEART, 60);
        });*/
    }
}
