package org.loomdev.loom.entity.animal;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.animal.Dolphin;
import org.loomdev.api.world.Location;

public class DolphinImpl extends AbstractWaterAnimalImpl implements Dolphin {

    public DolphinImpl(net.minecraft.world.entity.animal.Dolphin entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Dolphin> getType() {
        return EntityType.DOLPHIN;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Dolphin getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Dolphin) super.getMinecraftEntity();
    }

    @Override
    public Location getTreasureLocation() {
        var pos = getMinecraftEntity().getTreasurePos();
        return new Location(null, pos.getX(), pos.getY(), pos.getZ()); // TODO world
    }

    @Override
    public void setTreasureLocation(Location location) {
        getMinecraftEntity().setTreasurePos(new BlockPos(location.getX(), location.getY(), location.getZ()));
    }

    @Override
    public boolean hasFish() {
        return getMinecraftEntity().gotFish();
    }

    @Override
    public void setHasFish(boolean flag) {
        getMinecraftEntity().setGotFish(flag);
    }

    @Override
    public int getMoistness() {
        return getMinecraftEntity().getMoistnessLevel();
    }

    @Override
    public void setMoistness(int i) {
        getMinecraftEntity().setMoisntessLevel(i);
    }
}
