package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.SkeletonEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Skeleton;

public class SkeletonImpl extends AbstractSkeletonImpl implements Skeleton {

    public SkeletonImpl(SkeletonEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SKELETON;
    }

    @Override
    public @NotNull SkeletonEntity getMinecraftEntity() {
        return (SkeletonEntity) super.getMinecraftEntity();
    }
}
