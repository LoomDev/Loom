package org.loomdev.loom.entity.raid;

import net.minecraft.entity.raid.RaiderEntity;
import org.loomdev.api.entity.raid.Raider;
import org.loomdev.loom.entity.mob.PatrolEntityImpl;

public class RaiderImpl extends PatrolEntityImpl implements Raider {

    public RaiderImpl(RaiderEntity entity) {
        super(entity);
    }

    @Override
    public RaiderEntity getMinecraftEntity() {
        return (RaiderEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean canJoinRaid() {
        return getMinecraftEntity().canJoinRaid();
    }

    @Override
    public void setCanJoinRaid(boolean b) {
        getMinecraftEntity().setAbleToJoinRaid(b);
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
        return getMinecraftEntity().dataTracker.get(RaiderEntity.CELEBRATING);
    }

    @Override
    public void setCelebrating(boolean flag) {
        getMinecraftEntity().setCelebrating(flag);
    }
}
