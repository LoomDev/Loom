package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.animal.Squid;

public class SquidImpl extends AbstractWaterAnimalImpl implements Squid {

    public SquidImpl(net.minecraft.world.entity.animal.Squid entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<? extends Squid> getType() {
        return EntityType.SQUID;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Squid getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Squid) super.getMinecraftEntity();
    }
}
