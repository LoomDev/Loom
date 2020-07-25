package org.loomdev.loom.command.loom;

import com.google.inject.internal.cglib.core.$ProcessArrayCallback;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.minecraft.util.Hand;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.Loom;
import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemTypes;
import org.loomdev.api.item.property.ItemProperties;
import org.loomdev.api.scheduler.TaskRunnable;
import org.loomdev.api.util.ChatColor;
import org.loomdev.api.world.Location;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.item.ItemStackImpl;
import org.loomdev.loom.world.WorldImpl;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class DebugCommand extends Command {

    public DebugCommand() {
        super("debug");
    }

    private int i = 0;
    BossBar bar;
    int something = 120;

    private static class TaskThing extends TaskRunnable {

        int i = 0;

        @Override
        public void run() {
            if (i < 5) {
                i++;
                Loom.getServer().broadcastMessage("Running TaskRunnable.");
            } else {
                this.cancel();
            }
        }
    }

    @Override
    public void execute(@NonNull CommandSource source, String[] args) {

        Player player = ((Player) source);

        source.sendMessage("This world's UUID is " + player.getWorld().getUUID().toString());
        // ((PlayerImpl) commandSource).setTime(Integer.parseInt(strings[0]), false);
    }

    private static final SecureRandom random = new SecureRandom();

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
