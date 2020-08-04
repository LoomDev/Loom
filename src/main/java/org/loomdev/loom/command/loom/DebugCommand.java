package org.loomdev.loom.command.loom;

import net.kyori.adventure.text.TextComponent;
import net.minecraft.util.Hand;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.item.Enchantment;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemTypes;
import org.loomdev.api.item.property.ItemProperties;
import org.loomdev.api.scheduler.TaskRunnable;
import org.loomdev.api.util.ChatColor;
import org.loomdev.api.world.World;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.item.ItemStackImpl;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DebugCommand extends Command {

    public DebugCommand() {
        super("debug");
    }

    @Override
    public void execute(@NonNull CommandSource source, String[] args) {
        Player player = ((Player) source);
        player.getWorld().spawnEntity(EntityType.ARMOR_STAND, player.getLocation()).ifPresent(entity -> {
            entity.setCustomName(TextComponent.of("Just an Armor Stand"));
            entity.setCustomNameVisible(true);
            player.sendMessage("Spawned entity.");
            Loom.getServer().broadcastMessage(entity.getLocation().toString());
        });

        /*ItemStack itemStack = ItemStack.builder()
                .type(ItemTypes.DIAMOND_SWORD)
                .amount(21)
                .name(TextComponent.of("Name test").color(ChatColor.GOLD))
//                .property(ItemProperties.Lore, data -> {
//                    List<Component> lore = new ArrayList<>();
//                    lore.add(TextComponent.of("Lore test 1").color(ChatColor.RED));
//                    lore.add(TextComponent.of("Lore test 2").color(ChatColor.GREEN));
//                    lore.add(TextComponent.of("Lore test 3").color(ChatColor.BLUE));
//                    data.setLore(lore);
                .lore(
//                })
                    TextComponent.of("Lore test 1").color(ChatColor.RED),
                    TextComponent.of("Lore test 2").color(ChatColor.GREEN),
                    TextComponent.of("Lore test 3").color(ChatColor.BLUE)
                )
                .appendLore(TextComponent.of("The lore of a pleb").color(ChatColor.LIGHT_PURPLE))
                .property(ItemProperties.Damage, data -> {
                    data.setDamage(data.getMaxDamage() / 2);
                })
                .build();

        itemStack.setName(TextComponent.of("Some random name? that works?").color(ChatColor.YELLOW));
        itemStack.addEnchantment(Enchantment.SHARPNESS, 4);
        itemStack.clearEnchantments();
        itemStack.removeCustomName();

        p.sendMessage(itemStack.getHoverText());
        //p.getMinecraftEntity().updateCursorStack();
        p.getMinecraftEntity().setStackInHand(Hand.MAIN_HAND, ((ItemStackImpl) itemStack).getMinecraftItemStack());*/
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
