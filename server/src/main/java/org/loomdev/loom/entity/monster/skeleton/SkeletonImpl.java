package org.loomdev.loom.entity.monster.skeleton;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.skeleton.Skeleton;

public class SkeletonImpl extends AbstractSkeletonImpl implements Skeleton {

    public SkeletonImpl(net.minecraft.world.entity.monster.Skeleton entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Skeleton> getType() {
        return EntityType.SKELETON;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Skeleton getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Skeleton) super.getMinecraftEntity();
    }
}
