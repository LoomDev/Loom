package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.StriderEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Strider;
import org.loomdev.api.sound.SoundCategory;

import java.util.Optional;

public class StriderImpl extends AnimalEntityImpl implements Strider {

    public StriderImpl(StriderEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.STRIDER;
    }

    @NotNull
    @Override
    public StriderEntity getMinecraftEntity() {
        return (StriderEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isCold() {
        return getMinecraftEntity().isCold();
    }

    @Override
    public void setCold(boolean flag) {
        getMinecraftEntity().setCold(flag);
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
