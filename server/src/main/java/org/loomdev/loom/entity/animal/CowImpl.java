package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.animal.Cow;

public class CowImpl extends AnimalImpl implements Cow {

    public CowImpl(net.minecraft.world.entity.animal.Cow entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<? extends Cow> getType() {
        return EntityType.COW;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Cow getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Cow) super.getMinecraftEntity();
    }
}
