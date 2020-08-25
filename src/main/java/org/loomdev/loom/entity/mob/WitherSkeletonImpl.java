package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.WitherSkeletonEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.WitherSkeleton;

public class WitherSkeletonImpl extends AbstractSkeletonImpl implements WitherSkeleton {

    public WitherSkeletonImpl(WitherSkeletonEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.WITHER_SKELETON;
    }

    @Override
    public @NotNull WitherSkeletonEntity getMinecraftEntity() {
        return (WitherSkeletonEntity) super.getMinecraftEntity();
    }
}
