package org.loomdev.loom.entity.animal.fish;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.animal.fish.Salmon;

public class SalmonImpl extends AbstractSchoolingFishImpl implements Salmon {

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
