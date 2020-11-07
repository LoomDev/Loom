package org.loomdev.loom.entity.animal.horse;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.animal.horse.Mule;

public class MuleImpl extends AbstractChestedHorseImpl implements Mule {

    public MuleImpl(net.minecraft.world.entity.animal.horse.Mule entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Mule> getType() {
        return EntityType.MULE;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.horse.Mule getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.horse.Mule) super.getMinecraftEntity();
    }
}
