package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Ocelot;

public class OcelotImpl extends AnimalImpl implements Ocelot {

    public OcelotImpl(net.minecraft.world.entity.animal.Ocelot entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Ocelot> getType() {
        return EntityType.OCELOT;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Ocelot getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Ocelot) super.getMinecraftEntity();
    }
}
