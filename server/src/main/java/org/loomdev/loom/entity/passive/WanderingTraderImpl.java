package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.WanderingTrader;
import org.loomdev.api.world.Location;

public class WanderingTraderImpl extends AbstractTraderImpl implements WanderingTrader {

    public WanderingTraderImpl(WanderingTraderEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<WanderingTrader> getType() {
        return EntityType.WANDERING_TRADER;
    }

    @Override
    public @NotNull WanderingTraderEntity getMinecraftEntity() {
        return (WanderingTraderEntity) super.getMinecraftEntity();
    }

    @Override
    public @Nullable Location getWanderingTarget() {
        BlockPos pos = getMinecraftEntity().wanderTarget;
        return pos == null ? null : new Location(null, pos.getX(), pos.getY(), pos.getZ()); // TODO replace with location without world
    }

    @Override
    public void setWanderingTarget(@Nullable Location location) { // TODO replace with location without world
        getMinecraftEntity().wanderTarget = location == null ? null : new BlockPos(location.getX(), location.getY(), location.getY());
    }

    @Override
    public int getDespawnDelay() {
        return getMinecraftEntity().getDespawnDelay();
    }

    @Override
    public void setDespawnDelay(int despawnDelay) {
        getMinecraftEntity().setDespawnDelay(despawnDelay);
    }
}
