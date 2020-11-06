package org.loomdev.loom.entity;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.passive.AgeableMob;

public abstract class AgeableMobImpl extends PathfinderMob implements AgeableMob {

    public AgeableMobImpl(net.minecraft.world.entity.AgeableMob entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.AgeableMob getMinecraftEntity() {
        return (net.minecraft.world.entity.AgeableMob) super.getMinecraftEntity();
    }
}
