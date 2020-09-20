package org.loomdev.loom.entity.vehicle;

import net.minecraft.entity.vehicle.ChestMinecartEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.ChestMinecart;

public class ChestMinecartImpl extends StorageMinecartImpl implements ChestMinecart {

    public ChestMinecartImpl(ChestMinecartEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<ChestMinecart> getType() {
        return EntityType.CHEST_MINECART;
    }

    @Override
    public @NotNull ChestMinecartEntity getMinecraftEntity() {
        return (ChestMinecartEntity) super.getMinecraftEntity();
    }
}
