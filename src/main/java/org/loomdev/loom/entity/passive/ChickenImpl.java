package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.ChickenEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Chicken;

public class ChickenImpl extends AnimalEntityImpl implements Chicken {

    public ChickenImpl(ChickenEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
        return EntityType.CHICKEN;
    }

    @Override
    public ChickenEntity getMinecraftEntity() {
        return (ChickenEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean hasJockey() {
        return getMinecraftEntity().hasJockey();
    }

    @Override
    public int getEggLayTime() {
        return getMinecraftEntity().eggLayTime;
    }

    @Override
    public void setEggLayTime(int ticks) {
        getMinecraftEntity().eggLayTime = ticks;
    }
}
