package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.PigEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Pig;
import org.loomdev.api.sound.SoundCategory;

import java.util.Optional;

public class PigImpl extends AnimalEntityImpl implements Pig {

    public PigImpl(PigEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PIG;
    }

    @Override
    public @NotNull PigEntity getMinecraftEntity() {
        return (PigEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean canBeSaddled() {
        return getMinecraftEntity().canBeSaddled();
    }

    @Override
    public void saddle(@Nullable SoundCategory soundCategory) {
        getMinecraftEntity().saddle(
                Optional.ofNullable(soundCategory)
                        .map(sc -> net.minecraft.sound.SoundCategory.getByName(sc.getName()))
                        .orElse(null)
        );
    }

    @Override
    public boolean isSaddled() {
        return getMinecraftEntity().isSaddled();
    }
}
