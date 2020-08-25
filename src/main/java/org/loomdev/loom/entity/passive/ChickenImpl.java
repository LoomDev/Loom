package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.ChickenEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Chicken;

public class ChickenImpl extends AnimalEntityImpl implements Chicken {

    public ChickenImpl(ChickenEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.CHICKEN;
    }

    @Override
    public @NotNull ChickenEntity getMinecraftEntity() {
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
