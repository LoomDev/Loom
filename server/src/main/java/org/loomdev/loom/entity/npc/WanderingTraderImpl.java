package org.loomdev.loom.entity.npc;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.npc.WanderingTrader;
import org.loomdev.api.world.Location;

public class WanderingTraderImpl extends AbstractVillagerImpl implements WanderingTrader {

    public WanderingTraderImpl(net.minecraft.world.entity.npc.WanderingTrader entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<WanderingTrader> getType() {
        return EntityType.WANDERING_TRADER;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.npc.WanderingTrader getMinecraftEntity() {
        return (net.minecraft.world.entity.npc.WanderingTrader) super.getMinecraftEntity();
    }

    @Override
    public @Nullable Location getWanderingTarget() {
        var pos = getMinecraftEntity().wanderTarget;
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
