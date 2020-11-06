package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Chicken;

public class ChickenImpl extends AnimalImpl implements Chicken {

    public ChickenImpl(net.minecraft.world.entity.animal.Chicken entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Chicken> getType() {
        return EntityType.CHICKEN;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Chicken getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Chicken) super.getMinecraftEntity();
    }

    @Override
    public boolean hasJockey() {
        return getMinecraftEntity().isChickenJockey();
    }

    @Override
    public int getEggLayTime() {
        return getMinecraftEntity().eggTime;
    }

    @Override
    public void setEggLayTime(int ticks) {
        getMinecraftEntity().eggTime = ticks;
    }
}
