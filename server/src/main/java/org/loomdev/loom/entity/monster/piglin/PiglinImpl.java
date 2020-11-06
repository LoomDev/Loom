package org.loomdev.loom.entity.monster.piglin;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Piglin;

public class PiglinImpl extends AbstractPiglinImpl implements Piglin {

    public PiglinImpl(net.minecraft.world.entity.monster.piglin.Piglin entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Piglin> getType() {
        return EntityType.PIGLIN;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.piglin.Piglin getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.piglin.Piglin) super.getMinecraftEntity();
    }

    @Override
    public boolean isDancing() {
        return getMinecraftEntity().entityData.get(net.minecraft.world.entity.monster.piglin.Piglin.DATA_IS_DANCING);
    }

    @Override
    public void setDancing(boolean flag) {
        getMinecraftEntity().setDancing(flag);
    }

    @Override
    public boolean isChargingCrossbow() {
        return getMinecraftEntity().entityData.get(net.minecraft.world.entity.monster.piglin.Piglin.DATA_IS_CHARGING_CROSSBOW);
    }

    @Override
    public void setChargingCrossbow(boolean flag) {
        getMinecraftEntity().setChargingCrossbow(flag);
    }

    @Override
    public boolean isBaby() {
        return getMinecraftEntity().entityData.get(net.minecraft.world.entity.monster.piglin.Piglin.DATA_BABY_ID);
    }

    @Override
    public void setBaby(boolean flag) {
        getMinecraftEntity().setBaby(flag);
    }
}
