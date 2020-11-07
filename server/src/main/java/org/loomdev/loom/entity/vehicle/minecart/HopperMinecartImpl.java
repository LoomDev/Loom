package org.loomdev.loom.entity.vehicle.minecart;

import net.minecraft.world.entity.vehicle.MinecartHopper;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.minecart.HopperMinecart;

public class HopperMinecartImpl extends AbstractStorageMinecartImpl implements HopperMinecart {

    public HopperMinecartImpl(MinecartHopper entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<HopperMinecart> getType() {
        return EntityType.HOPPER_MINECART;
    }

    @Override
    @NotNull
    public MinecartHopper getMinecraftEntity() {
        return (MinecartHopper) super.getMinecraftEntity();
    }
}
