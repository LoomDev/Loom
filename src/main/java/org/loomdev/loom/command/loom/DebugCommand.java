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
import org.loomdev.api.item.Enchantments;
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

        PlayerImpl p = (PlayerImpl) player;
        ItemStack itemStack = ItemStack.builder()
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
        itemStack.addEnchantment(Enchantments.SHARPNESS, 4);
        itemStack.clearEnchantments();
        itemStack.removeCustomName();

        p.sendMessage(itemStack.getHoverText());
        //p.getMinecraftEntity().updateCursorStack();
        p.getMinecraftEntity().setStackInHand(Hand.MAIN_HAND, ((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    private static final SecureRandom random = new SecureRandom();

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
