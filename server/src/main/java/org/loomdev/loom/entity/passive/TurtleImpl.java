package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.TurtleEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Turtle;

public class TurtleImpl extends AnimalEntityImpl implements Turtle {

    public TurtleImpl(TurtleEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.TURTLE;
    }

    @NotNull
    @Override
    public TurtleEntity getMinecraftEntity() {
        return (TurtleEntity) super.getMinecraftEntity();
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
