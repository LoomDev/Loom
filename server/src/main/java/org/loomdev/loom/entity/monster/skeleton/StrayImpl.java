package org.loomdev.loom.entity.monster.skeleton;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.skeleton.Stray;

public class StrayImpl extends AbstractSkeletonImpl implements Stray {

    public StrayImpl(net.minecraft.world.entity.monster.Stray entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Stray> getType() {
        return EntityType.STRAY;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Stray getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Stray) super.getMinecraftEntity();
    }
}
