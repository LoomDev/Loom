package org.loomdev.loom.entity.mob;

import com.google.common.base.Preconditions;
import net.minecraft.entity.mob.CreeperEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Creeper;
import org.loomdev.loom.event.LoomEventDispatcher;

public class CreeperImpl extends HostileEntityImpl implements Creeper {

    public CreeperImpl(CreeperEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.CREEPER;
    }

    @Override
    public CreeperEntity getMinecraftEntity() {
        return (CreeperEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isCharged() {
        return getMinecraftEntity().S_();
    }

    @Override
    public void setCharged(boolean charged) {
        LoomEventDispatcher.onCreeperCharged(getMinecraftEntity()).thenAccept(event -> {
            if (!event.isCancelled()) {
                getMinecraftEntity().setCharged(charged);
            }
        });
    }

    @Override
    public int getFuseTime() {
        return getMinecraftEntity().fuseTime;
    }

    @Override
    public void setFuseTime(int ticks) {
        Preconditions.checkArgument(ticks >= 0, "Fuse time is less than zero ticks.");
        getMinecraftEntity().fuseTime = ticks;
    }

    @Override
    public int getCurrentFuseTime() {
        return getMinecraftEntity().currentFuseTime;
    }

    @Override
    public float getExplosionPower() {
        return getMinecraftEntity().explosionPower;
    }

    @Override
    public void setExplosionPower(float power) {
        Preconditions.checkArgument(power >= 0, "Explosion power can't be less than zero.");
        getMinecraftEntity().explosionPower = power;
    }

    @Override
    public boolean isIgnited() {
        return getMinecraftEntity().getIgnited();
    }

    @Override
    public void ignite() {
        getMinecraftEntity().ignite();
    }

    @Override
    public void explode() {
        getMinecraftEntity().explode();
    }
}
