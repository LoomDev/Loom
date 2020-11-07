package org.loomdev.api.entity.projectile;

import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.LivingEntity;

import java.util.Optional;

public interface EvokerFangs extends Entity {

    @Nullable
    LivingEntity getOwner();

    void setOwner(@Nullable LivingEntity entity);
}
