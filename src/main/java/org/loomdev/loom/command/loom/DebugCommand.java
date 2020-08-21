package org.loomdev.loom.command.loom;

import com.google.common.collect.ImmutableList;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.Tnt;
import org.loomdev.api.entity.decoration.ArmorStand;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.entity.projectile.LargeFireball;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemType;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class DebugCommand extends Command {

    public DebugCommand() {
        super("debug");
    }

    @Override
    public void execute(@NotNull CommandSource source, String[] args) {
        Player player = ((Player) source);

        player.getWorld().spawnEntity(EntityType.TNT, player.getLocation()).ifPresent(entity -> {
            ((Tnt) entity).setExplosionPower(15);
            player.sendMessage("Spawned entity.");
        });
    }

    @Override
    public @NotNull List<String> suggest(@NotNull CommandSource source, @Nullable String[] args) {
        return ImmutableList.of();
    }

    private static final SecureRandom random = new SecureRandom();

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
