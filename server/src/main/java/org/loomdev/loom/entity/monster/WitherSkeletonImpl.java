package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.WitherSkeleton;

public class WitherSkeletonImpl extends AbstractSkeletonImpl implements WitherSkeleton {

    public WitherSkeletonImpl(net.minecraft.world.entity.monster.WitherSkeleton entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<WitherSkeleton> getType() {
        return EntityType.WITHER_SKELETON;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.WitherSkeleton getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.WitherSkeleton) super.getMinecraftEntity();
    }
}
