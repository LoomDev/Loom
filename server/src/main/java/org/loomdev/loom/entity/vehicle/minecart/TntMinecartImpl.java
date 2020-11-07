package org.loomdev.loom.entity.vehicle.minecart;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.vehicle.MinecartTNT;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.minecart.TntMinecart;

public class TntMinecartImpl extends AbstractMinecartImpl implements TntMinecart {

    public TntMinecartImpl(MinecartTNT entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<TntMinecart> getType() {
        return EntityType.TNT_MINECART;
    }

    @Override
    @NotNull
    public MinecartTNT getMinecraftEntity() {
        return (MinecartTNT) super.getMinecraftEntity();
    }

    @Override
    public int getRemainingFuseTicks() {
        return getMinecraftEntity().fuse;
    }

    @Override
    public void setRemainingFuseTicks(int ticks) {
        getMinecraftEntity().fuse = ticks;
    }

    @Override
    public float getExplosionPower() {
        return getMinecraftEntity().loomExplosionPower;
    }

    @Override
    public void setExplosionPower(int power) {
        Preconditions.checkArgument(power >= 0, "Explosion power can't be less than zero.");
        getMinecraftEntity().loomExplosionPower = power;
    }
}
