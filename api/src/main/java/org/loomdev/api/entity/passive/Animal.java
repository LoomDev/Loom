package org.loomdev.api.entity.passive;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.item.ItemStack;

import java.util.UUID;

public interface Animal extends AgeableMob {

    int getLoveTicks();

    void setLoveTicks(int ticks);

    void resetLoveTicks();

    boolean isInLove();

    UUID getBreedCause();

    void setBreedCause(@NotNull UUID uuid);

    boolean isBreedingItem(@NotNull ItemStack itemStack);
}
