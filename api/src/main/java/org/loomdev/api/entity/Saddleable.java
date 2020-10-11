package org.loomdev.api.entity;

import org.jetbrains.annotations.Nullable;
import org.loomdev.api.sound.SoundCategories;

public interface Saddleable {

    boolean canBeSaddled();

    void saddle(@Nullable SoundCategories soundCategory);

    boolean isSaddled();
}
