package org.loomdev.loom.entity.animal.golem;

import net.minecraft.sounds.SoundSource;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.animal.golem.SnowGolem;

public class SnowGolemImpl extends AbstractGolemImpl implements SnowGolem {

    public SnowGolemImpl(net.minecraft.world.entity.animal.SnowGolem entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<SnowGolem> getType() {
        return EntityType.SNOW_GOLEM;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.SnowGolem getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.SnowGolem) super.getMinecraftEntity();
    }

    @Override
    public boolean hasPumpkin() {
        return getMinecraftEntity().hasPumpkin();
    }

    @Override
    public void setHasPumpkin(boolean flag) {
        getMinecraftEntity().setPumpkin(flag);
    }

    @Override
    public boolean isShearable() {
        return getMinecraftEntity().readyForShearing();
    }

    @Override
    public void shear() {
        getMinecraftEntity().shear(SoundSource.NEUTRAL);
    }
}
