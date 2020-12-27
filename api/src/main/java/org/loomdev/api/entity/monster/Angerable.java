package org.loomdev.api.entity.monster;

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

    @NotNull
    Optional<UUID> getAngryAt();

    void setAngryAt(@Nullable UUID uuid);

    @NotNull
    Optional<LivingEntity> getTarget();

    void setTarget(@Nullable LivingEntity entity);

    void forgive(@NotNull Player player); // TODO not server player (npc)
}
