package org.loomdev.loom.command.loom;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.Loom;
import org.loomdev.api.bossbar.BossBar;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.effect.AreaEffectCloudEntity;
import org.loomdev.api.entity.effect.StatusEffect;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.scheduler.Task;
import org.loomdev.api.util.Color;
import org.loomdev.api.world.Location;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

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

        Location location = ((Player) commandSource).getLocation();
        location.getWorld().flatMap(world -> world.spawnEntity(EntityType.AREA_EFFECT_CLOUD, location)).ifPresent(entity -> {
            AreaEffectCloudEntity area = (AreaEffectCloudEntity) entity;

            area.setColor(Color.fromRgb(51, 214, 214).asRgb());
            area.addStatusEffect(StatusEffect.builder(StatusEffect.Type.JUMP_BOOST)
                    .duration(20)
                    .amplifier(50)
                    .build());
        });
    }

    private static final SecureRandom random = new SecureRandom();
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
