package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.SkeletonHorseEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.SkeletonHorse;
import org.loomdev.loom.entity.passive.HorseBaseImpl;

public class SkeletonHorseImpl extends HorseBaseImpl implements SkeletonHorse {

    public SkeletonHorseImpl(SkeletonHorseEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SKELETON_HORSE;
    }

    @Override
    public @NotNull SkeletonHorseEntity getMinecraftEntity() {
        return (SkeletonHorseEntity) super.getMinecraftEntity();
    }
}
