package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.sound.SoundCategory;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.SnowGolem;
import org.loomdev.loom.GolemImpl;

public class SnowGolemImpl extends GolemImpl implements SnowGolem {

    public SnowGolemImpl(SnowGolemEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.SNOW_GOLEM;
    }

    @Override
    public @NotNull SnowGolemEntity getMinecraftEntity() {
        return (SnowGolemEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean hasPumpkin() {
        return getMinecraftEntity().hasPumpkin();
    }

    @Override
    public void setHasPumpkin(boolean flag) {
        getMinecraftEntity().setHasPumpkin(flag);
    }

    @Override
    public boolean isShearable() {
        return getMinecraftEntity().isShearable();
    }

    @Override
    public void shear() {
        getMinecraftEntity().sheared(SoundCategory.NEUTRAL);
    }
}
