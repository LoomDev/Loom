package org.loomdev.loom.entity.animal.horse;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.SkeletonHorse;

public class SkeletonHorseImpl extends AbstractHorseImpl implements SkeletonHorse {

    public SkeletonHorseImpl(net.minecraft.world.entity.animal.horse.SkeletonHorse entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<SkeletonHorse> getType() {
        return EntityType.SKELETON_HORSE;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.horse.SkeletonHorse getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.horse.SkeletonHorse) super.getMinecraftEntity();
    }
}
