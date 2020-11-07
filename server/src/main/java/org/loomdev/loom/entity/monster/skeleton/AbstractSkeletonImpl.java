package org.loomdev.loom.entity.monster.skeleton;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.monster.skeleton.AbstractSkeleton;
import org.loomdev.loom.entity.monster.MonsterImpl;

public abstract class AbstractSkeletonImpl extends MonsterImpl implements AbstractSkeleton {

    public AbstractSkeletonImpl(net.minecraft.world.entity.monster.AbstractSkeleton entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.AbstractSkeleton getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.AbstractSkeleton) super.getMinecraftEntity();
    }
}
