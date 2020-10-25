package org.loomdev.loom.command.loom;

import com.google.common.collect.ImmutableList;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.command.Command;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Creeper;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.sound.SoundCategory;
import org.loomdev.api.sound.SoundEvent;

import java.security.SecureRandom;
import java.util.List;

public class DebugCommand extends Command {

    public DebugCommand() {
        super("debug");
    }

    @Override
    public void execute(@NotNull CommandSource source, String[] args) {
        Player player = ((Player) source);

        player.playSound(Sound.builder(SoundEvent.ENTITY_WITCH_THROW).category(SoundCategory.HOSTILE).build());
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
