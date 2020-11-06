package org.loomdev.loom.entity.vehicle;

import net.minecraft.world.entity.vehicle.Minecart;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.vehicle.RideableMinecart;

public class MinecartImpl extends AbstractMinecartImpl implements RideableMinecart {

    public MinecartImpl(Minecart entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<RideableMinecart> getType() {
        return EntityType.MINECART;
    }

    @Override
    @NotNull
    public Minecart getMinecraftEntity() {
        return (Minecart) super.getMinecraftEntity();
    }
}
