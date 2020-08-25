package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Dolphin;
import org.loomdev.api.world.Location;

public class DolphinImpl extends WaterCreatureImpl implements Dolphin {
    public DolphinImpl(DolphinEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.DOLPHIN;
    }

    @Override
    public @NotNull DolphinEntity getMinecraftEntity() {
        return (DolphinEntity) super.getMinecraftEntity();
    }

    @Override
    public Location getTreasureLocation() {
        BlockPos pos = getMinecraftEntity().getTreasurePos();
        return new Location(null, pos.getX(), pos.getY(), pos.getZ()); // TODO world
    }

    @Override
    public void setTreasureLocation(Location location) {
        getMinecraftEntity().setTreasurePos(new BlockPos(location.getX(), location.getY(), location.getZ()));
    }

    @Override
    public boolean hasFish() {
        return getMinecraftEntity().hasFish();
    }

    @Override
    public void setHasFish(boolean flag) {
        getMinecraftEntity().setHasFish(flag);
    }

    @Override
    public int getMoistness() {
        return getMinecraftEntity().getMoistness();
    }

    @Override
    public void setMoistness(int i) {
        getMinecraftEntity().setMoistness(i);
    }
}
