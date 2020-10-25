package org.loomdev.api.sound;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.Loom;
import org.loomdev.api.util.registry.Keyed;

public interface SoundCategory extends Keyed {

    // region SoundCategories

    SoundCategory MASTER = getByName("master");
    SoundCategory MUSIC = getByName("music");
    SoundCategory RECORDS = getByName("record");
    SoundCategory WEATHER = getByName("weather");
    SoundCategory BLOCKS = getByName("block");
    SoundCategory HOSTILE = getByName("hostile");
    SoundCategory NEUTRAL = getByName("neutral");
    SoundCategory PLAYERS = getByName("player");
    SoundCategory AMBIENT = getByName("ambient");
    SoundCategory VOICE = getByName("voice");

    // endregion SoundCategories

    static SoundCategory getByName(String name) {
        return Loom.getRegistry().getWrapped(SoundCategory.class, name);
    }

    @NotNull String getName();
}
