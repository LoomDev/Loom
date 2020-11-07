package org.loomdev.loom.entity.monster;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.monster.Strider;
import org.loomdev.api.sound.SoundSource;
import org.loomdev.loom.entity.animal.AnimalImpl;

import java.util.Optional;

public class StriderImpl extends AnimalImpl implements Strider {

    public StriderImpl(net.minecraft.world.entity.monster.Strider entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Strider> getType() {
        return EntityType.STRIDER;
    }

    @NotNull
    @Override
    public net.minecraft.world.entity.monster.Strider getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.Strider) super.getMinecraftEntity();
    }

    @Override
    public boolean isShivering() {
        return getMinecraftEntity().isSuffocating();
    }

    @Override
    public void setShivering(boolean flag) {
        getMinecraftEntity().setSuffocating(flag);
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
