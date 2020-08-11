package org.loomdev.loom.entity.mob;

import com.google.common.base.Preconditions;
import net.minecraft.entity.mob.CreeperEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.mob.Creeper;
import org.loomdev.loom.event.LoomEventDispatcher;

public class CreeperImpl extends HostileEntityImpl implements Creeper {

    public CreeperImpl(CreeperEntity entity) {
        super(entity);
    }

    @Override
    public @NonNull EntityType getType() {
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
    public int getExplosionRadius() {
        return getMinecraftEntity().explosionRadius;
    }

    @Override
    public void setExplosionRadius(int radius) {
        Preconditions.checkArgument(radius >= 0, "Explosion radius is less than zero blocks.");
        getMinecraftEntity().explosionRadius = radius;
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
