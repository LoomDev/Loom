package org.loomdev.loom.entity.monster;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.Creeper;
import org.loomdev.loom.event.LoomEventDispatcher;

public class CreeperImpl extends MonsterImpl implements Creeper {

    public CreeperImpl(net.minecraft.world.entity.monster.Creeper entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Creeper> getType() {
        return EntityType.CREEPER;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.Creeper getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Creeper) super.getMinecraftEntity();
    }

    @Override
    public boolean isCharged() {
        return getMinecraftEntity().isPowered();
    }

    @Override
    public void setCharged(boolean charged) {
        if (charged && !LoomEventDispatcher.onCreeperCharge(getMinecraftEntity()).isCancelled()) {
            getMinecraftEntity().setCharged(true);
            return;
        }

        getMinecraftEntity().setCharged(false);
    }

    @Override
    public int getFuseTime() {
        return getMinecraftEntity().maxSwell;
    }

    @Override
    public void setFuseTime(int ticks) {
        Preconditions.checkArgument(ticks >= 0, "Fuse time is less than zero ticks.");
        getMinecraftEntity().maxSwell = ticks;
    }

    @Override
    public int getCurrentFuseTime() {
        return getMinecraftEntity().swell;
    }

    @Override
    public float getExplosionPower() {
        return getMinecraftEntity().explosionPower;
    }

    @Override
    public void setExplosionPower(int power) {
        Preconditions.checkArgument(power >= 0, "Explosion power can't be less than zero.");
        getMinecraftEntity().explosionPower = power;
    }

    @Override
    public boolean isIgnited() {
        return getMinecraftEntity().isIgnited();
    }

    @Override
    public void ignite() {
        getMinecraftEntity().ignite();
    }

    @Override
    public void explode() {
        getMinecraftEntity().explodeCreeper();
    }
}
