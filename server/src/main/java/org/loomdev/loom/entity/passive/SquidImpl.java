package org.loomdev.loom.entity.passive;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Squid;

public class SquidImpl extends WaterAnimalImpl implements Squid {

    public SquidImpl(net.minecraft.world.entity.animal.Squid entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Squid> getType() {
        return EntityType.SQUID;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Squid getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Squid) super.getMinecraftEntity();
    }
}
