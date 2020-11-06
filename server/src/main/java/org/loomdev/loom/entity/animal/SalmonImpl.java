package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Salmon;

public class SalmonImpl extends SchoolingFishImpl implements Salmon {

    public SalmonImpl(net.minecraft.world.entity.animal.Salmon entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Salmon> getType() {
        return EntityType.SALMON;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Salmon getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Salmon) super.getMinecraftEntity();
    }
}
