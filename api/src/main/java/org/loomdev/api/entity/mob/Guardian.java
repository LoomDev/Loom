package org.loomdev.api.entity.mob;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.LivingEntity;

import java.util.Optional;

public interface Guardian extends Monster {

    boolean isSpikesRetracted();

    void setSpikesRetracted(boolean flag);

    @Nullable
    LivingEntity getBeamTarget();

    void setBeamTarget(@Nullable LivingEntity entity);

    boolean hasBeamTarget();

    // TODO v2 beam progress, ticks and WarmupTime
}
