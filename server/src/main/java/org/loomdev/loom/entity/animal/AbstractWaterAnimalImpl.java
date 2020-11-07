package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.monster.WaterAnimal;
import org.loomdev.loom.entity.PathfinderMob;

public abstract class AbstractWaterAnimalImpl extends PathfinderMob implements WaterAnimal {

    public AbstractWaterAnimalImpl(net.minecraft.world.entity.animal.WaterAnimal entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.WaterAnimal getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.WaterAnimal) super.getMinecraftEntity();
    }
}
