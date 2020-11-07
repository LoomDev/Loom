package org.loomdev.loom.entity.vehicle.minecart;

import net.minecraft.world.entity.vehicle.MinecartFurnace;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.minecart.FurnaceMinecart;

public class FurnaceMinecartImpl extends AbstractMinecartImpl implements FurnaceMinecart {

    public FurnaceMinecartImpl(MinecartFurnace entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<FurnaceMinecart> getType() {
        return EntityType.FURNACE_MINECART;
    }

    @Override
    @NotNull
    public MinecartFurnace getMinecraftEntity() {
        return (MinecartFurnace) super.getMinecraftEntity();
    }
}
