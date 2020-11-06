package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Cod;

public class CodImpl extends SchoolingFishImpl implements Cod {

    public CodImpl(net.minecraft.world.entity.animal.Cod entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Cod> getType() {
        return EntityType.COD;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Cod getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Cod) super.getMinecraftEntity();
    }
}
