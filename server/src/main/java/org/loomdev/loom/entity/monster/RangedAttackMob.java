package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.LivingEntity;

public interface RangedAttackMob {

    void performRangedAttack(@NotNull LivingEntity entity, float f);
}
