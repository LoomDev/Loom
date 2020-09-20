package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.AbstractSkeletonEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.mob.AbstractSkeleton;

public abstract class AbstractSkeletonImpl extends HostileEntityImpl implements AbstractSkeleton {

    public AbstractSkeletonImpl(AbstractSkeletonEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull AbstractSkeletonEntity getMinecraftEntity() {
        return (AbstractSkeletonEntity) super.getMinecraftEntity();
    }
}
