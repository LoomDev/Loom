package org.loomdev.loom.entity.passive;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.mob.WaterAnimal;
import org.loomdev.loom.entity.PathfinderMob;

public abstract class WaterAnimalImpl extends PathfinderMob implements WaterAnimal {

    public WaterAnimalImpl(net.minecraft.world.entity.animal.WaterAnimal entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.WaterAnimal getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.WaterAnimal) super.getMinecraftEntity();
    }
}
