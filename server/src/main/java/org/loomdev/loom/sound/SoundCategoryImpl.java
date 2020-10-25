package org.loomdev.loom.sound;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.sound.SoundCategory;
import org.loomdev.loom.util.registry.GenericWrapped;

public class SoundCategoryImpl extends GenericWrapped implements SoundCategory {

    public String name;

    public SoundCategoryImpl(String key) {
        super(key);
        this.name = key;
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }
}
