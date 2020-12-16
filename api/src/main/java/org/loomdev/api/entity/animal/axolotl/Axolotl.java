package org.loomdev.api.entity.animal.axolotl;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.animal.Animal;
import org.loomdev.api.entity.animal.Bucketable;

public interface Axolotl extends Animal, Bucketable {

    boolean isPlayingDead();

    void setPlayingDead(boolean playingDead);

    @NotNull
    Variant getVariant();

    void setVariant(@NotNull Variant variant);

    enum Variant {

        LUCY,
        WILD,
        GOLD,
        CYAN,
        BLUE
    }
}
