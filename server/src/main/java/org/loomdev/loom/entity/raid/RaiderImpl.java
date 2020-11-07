package org.loomdev.loom.entity.raid;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.raid.Raider;
import org.loomdev.loom.entity.monster.illager.AbstractPatrollingMonsterImpl;

public abstract class RaiderImpl extends AbstractPatrollingMonsterImpl implements Raider {

    public RaiderImpl(net.minecraft.world.entity.raid.Raider entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.raid.Raider getMinecraftEntity() {
        return (net.minecraft.world.entity.raid.Raider) super.getMinecraftEntity();
    }

    @Override
    public boolean canJoinRaid() {
        return getMinecraftEntity().canJoinRaid();
    }

    @Override
    public void setCanJoinRaid(boolean canJoin) {
        getMinecraftEntity().setCanJoinRaid(canJoin);
    }

    @Override
    public boolean hasActiveRaid() {
        return getMinecraftEntity().hasActiveRaid();
    }

    @Override
    public int getWave() {
        return getMinecraftEntity().getWave();
    }

    @Override
    public void setWave(int wave) {
        getMinecraftEntity().setWave(wave);
    }

    @Override
    public boolean isCelebrating() {
        return getMinecraftEntity().entityData.get(net.minecraft.world.entity.raid.Raider.IS_CELEBRATING);
    }

    @Override
    public void setCelebrating(boolean flag) {
        getMinecraftEntity().setCelebrating(flag);
    }
}
