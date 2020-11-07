package org.loomdev.loom.entity.vehicle.minecart;

import net.minecraft.world.entity.vehicle.MinecartChest;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.minecart.ChestMinecart;

public class ChestMinecartImpl extends AbstractStorageMinecartImpl implements ChestMinecart {

    public ChestMinecartImpl(MinecartChest entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType<ChestMinecart> getType() {
        return EntityType.CHEST_MINECART;
    }

    @Override
    @NotNull
    public MinecartChest getMinecraftEntity() {
        return (MinecartChest) super.getMinecraftEntity();
    }
}
