package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Turtle;

public class TurtleImpl extends AnimalImpl implements Turtle {

    public TurtleImpl(net.minecraft.world.entity.animal.Turtle entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Turtle> getType() {
        return EntityType.TURTLE;
    }

    @NotNull
    @Override
    public net.minecraft.world.entity.animal.Turtle getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Turtle) super.getMinecraftEntity();
    }

    @Override
    public boolean isBaby() {
        return getMinecraftEntity().isBaby();
    }

    @Override
    public void setBaby(boolean flag) {
        getMinecraftEntity().setBaby(flag);
    }
}
