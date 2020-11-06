package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Pig;
import org.loomdev.api.sound.SoundSource;

import java.util.Optional;

public class PigImpl extends AnimalImpl implements Pig {

    public PigImpl(net.minecraft.world.entity.animal.Pig entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Pig> getType() {
        return EntityType.PIG;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Pig getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Pig) super.getMinecraftEntity();
    }

    @Override
    public boolean canBeSaddled() {
        return getMinecraftEntity().isSaddleable();
    }

    @Override
    public void saddle(@Nullable SoundSource soundSource) {
        getMinecraftEntity().equipSaddle(
                Optional.ofNullable(soundSource)
                        .map(sc -> net.minecraft.sounds.SoundSource.getByName(sc.getName()))
                        .orElse(null)
        );
    }

    @Override
    public boolean isSaddled() {
        return getMinecraftEntity().isSaddled();
    }
}
