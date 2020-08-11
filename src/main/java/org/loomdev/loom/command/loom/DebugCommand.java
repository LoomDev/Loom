package org.loomdev.loom.command.loom;

import net.kyori.adventure.text.TextComponent;
import net.minecraft.util.Hand;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.block.BlockType;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.decoration.ArmorStand;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.item.Enchantment;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.item.property.ItemProperty;
import org.loomdev.api.util.ChatColor;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.item.ItemStackImpl;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class DebugCommand extends Command {

    public DebugCommand() {
        super("debug");
    }

    @Override
    public void execute(@NonNull CommandSource source, String[] args) {
        Player player = ((Player) source);

        player.getWorld().setAbsoluteTime(13000);

        player.getWorld().spawnEntity(EntityType.ARMOR_STAND, player.getLocation()).ifPresent(entity -> {
            entity.setCustomName(TextComponent.of("Just an Armor Stand"));
            entity.setCustomNameVisible(true);
            ((ArmorStand) entity).setHelmet(ItemStack.builder().type(ItemType.DIAMOND_HELMET).build());
            ((ArmorStand) entity).setArmsVisible(true);
            ((ArmorStand) entity).setItemInHand(org.loomdev.api.util.Hand.MAIN_HAND, ItemStack.builder().type(ItemType.SOUL_CAMPFIRE).build());
            player.sendMessage("Spawned entity.");
            Loom.getServer().broadcastMessage(entity.getLocation().toString());
        });
    }

    @Override
    public @NotNull List<String> suggest(@NotNull CommandSource source, @Nullable String[] args) {
        return Arrays.asList("uwu", "owo", "ewe", "qwq");
    }

    private static final SecureRandom random = new SecureRandom();

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
