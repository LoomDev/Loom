package org.loomdev.loom.command.loom;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.world.World;

import java.security.SecureRandom;
import java.util.List;

public class DebugCommand extends Command {

    public DebugCommand() {
        super("debug");
    }

    @Override
    public void execute(@NotNull CommandSource source, String[] args) {
        Player player = ((Player) source);

        player.getWorld().setBlock(player.getLocation(), BlockType.WAXED_COPPER);

        /*player.sendMessage("junge is a cunt");
        var cat = player.getWorld().spawnEntity(EntityType.CAT, player.getLocation());
        cat.setCustomName(Component.text("Junge is a cunt").color(ChatColor.GOLD));
        cat.setCustomNameVisible(true);
        cat.setCollarColor(DyeColor.RED);
        cat.setVariant(Cat.Variant.RAGDOLL);
        cat.setTamed(true);
        cat.setOwnerId(player.getUUID());

        StatusEffect.builder(StatusEffectType.LEVITATION)
                .amplifier(1)
                .duration(100)
                .applyTo(cat);*/

        //player.getWorld().setBlock(player.getLocation(), BlockType.YELLOW_WOOL);
    }

    @Override
    public @NotNull List<String> suggest(@NotNull CommandSource source, @Nullable String[] args) {
        return ImmutableList.of("junge", "is", "a", "gay", "cunt");
    }

    private static final SecureRandom random = new SecureRandom();

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
