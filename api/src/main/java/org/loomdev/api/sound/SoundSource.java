package org.loomdev.api.sound;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.util.registry.Keyed;
import org.loomdev.api.util.registry.Registry;

/**
 * Represents a sound source (a sound category).
 */
public interface SoundSource extends Keyed {

    // region SoundCategories

    SoundSource MASTER = getByName("master");
    SoundSource MUSIC = getByName("music");
    SoundSource RECORDS = getByName("record");
    SoundSource WEATHER = getByName("weather");
    SoundSource BLOCKS = getByName("block");
    SoundSource HOSTILE = getByName("hostile");
    SoundSource NEUTRAL = getByName("neutral");
    SoundSource PLAYERS = getByName("player");
    SoundSource AMBIENT = getByName("ambient");
    SoundSource VOICE = getByName("voice");

    // endregion SoundCategories

    static SoundSource getByName(String name) {
        return Registry.get().getWrapped(SoundSource.class, name);
    }

    @NotNull String getName();
}
