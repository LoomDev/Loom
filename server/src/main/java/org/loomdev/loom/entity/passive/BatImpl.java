package org.loomdev.loom.entity.passive;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Bat;
import org.loomdev.loom.entity.ambient.AmbientEntityImpl;

public class BatImpl extends AmbientEntityImpl implements Bat {

    public BatImpl(net.minecraft.world.entity.ambient.Bat entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Bat> getType() {
        return EntityType.BAT;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.ambient.Bat getMinecraftEntity() {
        return (net.minecraft.world.entity.ambient.Bat) super.getMinecraftEntity();
    }

    @Override
    public boolean isResting() {
        return getMinecraftEntity().isResting();
    }

    @Override
    public void setResting(boolean resting) {
        getMinecraftEntity().setResting(resting);
    }
}
