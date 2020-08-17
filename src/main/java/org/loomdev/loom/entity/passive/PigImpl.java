package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.PigEntity;
import net.minecraft.sound.SoundCategory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Pig;
import org.loomdev.api.sound.SoundCategories;

public class PigImpl extends AnimalEntityImpl implements Pig {

    public PigImpl(PigEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PIG;
    }

    @Override
    public PigEntity getMinecraftEntity() {
        return (PigEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean canBeSaddled() {
        return getMinecraftEntity().canBeSaddled();
    }

    @Override
    public void saddle(@Nullable SoundCategories soundCategories) {
        getMinecraftEntity().saddle(soundCategories == null ? null : SoundCategory.values()[soundCategories.ordinal()]);
    }

    @Override
    public boolean isSaddled() {
        return getMinecraftEntity().isSaddled();
    }
}
