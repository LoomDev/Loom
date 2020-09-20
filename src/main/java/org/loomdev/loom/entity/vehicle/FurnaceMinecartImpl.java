package org.loomdev.loom.entity.vehicle;

import net.minecraft.entity.vehicle.FurnaceMinecartEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.FurnaceMinecart;

public class FurnaceMinecartImpl extends MinecartImpl implements FurnaceMinecart {

    public FurnaceMinecartImpl(FurnaceMinecartEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<FurnaceMinecart> getType() {
        return EntityType.FURNACE_MINECART;
    }

    @Override
    public @NotNull FurnaceMinecartEntity getMinecraftEntity() {
        return (FurnaceMinecartEntity) super.getMinecraftEntity();
    }
}
