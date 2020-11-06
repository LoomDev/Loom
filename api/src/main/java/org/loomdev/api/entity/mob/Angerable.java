package org.loomdev.api.entity.mob;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.player.Player;

import java.util.Optional;
import java.util.UUID;

public interface Angerable {

    int getAngerTime();

    void setAngerTime(int ticks);

    boolean isAngry();

    void stopAnger();

    @Nullable
    UUID getAngryAt();

    void setAngryAt(@Nullable UUID uuid);

    @Nullable
    LivingEntity getTarget();

    void setTarget(@NotNull LivingEntity entity);

    void forgive(@NotNull Player player); // TODO not server player (npc)
}
