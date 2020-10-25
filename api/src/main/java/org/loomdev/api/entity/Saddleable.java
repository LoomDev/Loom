package org.loomdev.api.entity;

import org.jetbrains.annotations.Nullable;
import org.loomdev.api.sound.SoundCategory;

public interface Saddleable {

    boolean canBeSaddled();

    void saddle(@Nullable SoundCategory soundCategory);

    boolean isSaddled();
}
