package org.loomdev.api.entity;

import org.jetbrains.annotations.Nullable;
import org.loomdev.api.sound.SoundSource;

public interface Saddleable {

    boolean canBeSaddled();

    void saddle(@Nullable SoundSource soundSource);

    boolean isSaddled();
}
