package org.loomdev.loom.sound;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.sound.SoundSource;
import org.loomdev.loom.util.registry.GenericWrapped;

public class SoundSourceImpl extends GenericWrapped implements SoundSource {

    public String name;

    public SoundSourceImpl(String key) {
        super(key);
        this.name = key;
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }
}
