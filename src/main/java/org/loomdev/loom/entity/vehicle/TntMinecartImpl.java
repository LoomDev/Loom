package org.loomdev.loom.entity.vehicle;

import com.google.common.base.Preconditions;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.TntMinecart;

public class TntMinecartImpl extends MinecartImpl implements TntMinecart {

    public TntMinecartImpl(TntMinecartEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TNT_MINECART;
    }

    @Override
    public @NotNull TntMinecartEntity getMinecraftEntity() {
        return (TntMinecartEntity) super.getMinecraftEntity();
    }

    @Override
    public int getRemainingFuseTicks() {
        return getMinecraftEntity().fuseTicks;
    }

    @Override
    public void setRemainingFuseTicks(int ticks) {
        getMinecraftEntity().fuseTicks = ticks;
    }

    @Override
    public float getExplosionPower() {
        return getMinecraftEntity().loomExplosionPower;
    }

    @Override
    public void setExplosionPower(float power) {
        Preconditions.checkArgument(power >= 0, "Explosion power can't be less than zero.");
        getMinecraftEntity().loomExplosionPower = power;
    }

}
