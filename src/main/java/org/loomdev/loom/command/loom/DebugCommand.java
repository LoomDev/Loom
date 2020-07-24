package org.loomdev.loom.command.loom;

import com.google.inject.internal.cglib.core.$ProcessArrayCallback;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minecraft.util.Hand;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemTypes;
import org.loomdev.api.item.property.ItemProperties;
import org.loomdev.api.util.ChatColor;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.item.ItemStackImpl;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class DebugCommand extends Command {

    public DebugCommand() {
        super("debug");
    }

    @Override
    public void execute(@NonNull CommandSource commandSource, String[] strings) {
        // Do debug shit

        /*Loom.getServer().getOnlinePlayers().forEach(player -> {
            if (player.isConnected()) {
                player.kick(TextComponent.of("you're a stupid cunt").color(TextColor.of(241, 140, 255)));
            }
        });*/

        //((Player) commandSource).getLocation().getWorld()

//        BossBar bar = Loom.getServer().createBossBar(
//                TextComponent.of("hehe loom owo uwu").color(TextColor.of(255, 255, 0)).decoration(TextDecoration.BOLD, true),
//                BossBar.Color.PINK,
//                BossBar.Style.PROGRESS
//        );
//        Loom.getServer().getOnlinePlayers().forEach(bar::addPlayer);
//
//        Task.builder().execute(() -> {
//            System.out.println("Changing bar color");
//            BossBar.Color color = randomEnum(BossBar.Color.class);
//            BossBar.Style style = randomEnum(BossBar.Style.class);
//
//            bar.setColor(color);
//            bar.setStyle(style);
//        }).intervalTicks(20).complete(null);

        /*BossBar bar = new BossBar
                .text()
                .build();*/

        /*Loom.getServer().getWorld("world").ifPresent(world -> {
            world.playSound(((PlayerImpl) commandSource).getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1.0f);
            commandSource.sendMessage("Played sound.");

            world.displayParticle(((PlayerImpl) commandSource).getLocation(), ParticleEffect.HEART, 60);
        });*/

//        Location location = ((Player) commandSource).getLocation();
//        location.getWorld().flatMap(world -> world.spawnEntity(EntityType.AREA_EFFECT_CLOUD, location)).ifPresent(entity -> {
//            AreaEffectCloudEntity area = (AreaEffectCloudEntity) entity;
//
//            area.setColor(Color.fromRgb(51, 214, 214).asRgb());
//            area.addStatusEffect(StatusEffect.builder(StatusEffect.Type.JUMP_BOOST)
//                    .duration(20)
//                    .amplifier(50)
//                    .build());
//        });

        Player player = (Player) commandSource;
        PlayerImpl p = (PlayerImpl) player;

//        net.minecraft.item.ItemStack mcStack = p.getMinecraftEntity().getStackInHand(Hand.MAIN_HAND);
//
//        ItemStack itemStack = new ItemStackImpl(mcStack);
//        itemStack.setType(ItemTypes.COMPASS);

        ItemStack itemStack = ItemStack.builder()
                .type(ItemTypes.DRAGON_EGG)
                .amount(21)
                .name(TextComponent.of("Name test").color(ChatColor.GOLD))
//                .property(ItemProperties.Lore, data -> {
//                    List<Component> lore = new ArrayList<>();
//                    lore.add(TextComponent.of("Lore test 1").color(ChatColor.RED));
//                    lore.add(TextComponent.of("Lore test 2").color(ChatColor.GREEN));
//                    lore.add(TextComponent.of("Lore test 3").color(ChatColor.BLUE));
//                    data.setLore(lore);
//                })
                .lore(
                    TextComponent.of("Lore test 1").color(ChatColor.RED),
                    TextComponent.of("Lore test 2").color(ChatColor.GREEN),
                    TextComponent.of("Lore test 3").color(ChatColor.BLUE)
                )
                .appendLore(TextComponent.of("The lore of a pleb").color(ChatColor.LIGHT_PURPLE))
                .build();

        p.getMinecraftEntity().setStackInHand(Hand.MAIN_HAND, ((ItemStackImpl) itemStack).getMinecraftItemStack());
        //p.getMinecraftEntity().updateCursorStack();
        p.getMinecraftEntity().inventory.updateItems();
    }

    private static final SecureRandom random = new SecureRandom();
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
