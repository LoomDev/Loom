package org.loomdev.loom.entity.animal.horse;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.passive.AbstractHorse;
import org.loomdev.api.sound.SoundSource;
import org.loomdev.loom.entity.animal.AnimalImpl;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

public abstract class AbstractHorseImpl extends AnimalImpl implements AbstractHorse {

    public AbstractHorseImpl(net.minecraft.world.entity.animal.horse.AbstractHorse entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.horse.AbstractHorse getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.horse.AbstractHorse) super.getMinecraftEntity();
    }

    @Override
    public boolean isJumping() {
        return getMinecraftEntity().isJumping();
    }

    @Override
    public void setJumping(boolean b) {
        getMinecraftEntity().setIsJumping(b);
    }

    @Override
    public boolean isTame() {
        return getMinecraftEntity().isTamed();
    }

    @Override
    public void setTame(boolean tamed) {
        getMinecraftEntity().setTamed(tamed);
    }

    @Override
    public boolean isEatingGrass() {
        return getMinecraftEntity().isEating();
    }

    @Override
    public boolean isAngry() {
        return getMinecraftEntity().isAggressive();
    }

    @Override
    public boolean isBred() {
        return getMinecraftEntity().isBred();
    }

    @Override
    public void setBred(boolean b) {
        getMinecraftEntity().setBred(b);
    }

    @Override
    public int getTemper() {
        return getMinecraftEntity().getTemper();
    }

    @Override
    public void setTemper(int i) {
        getMinecraftEntity().setTemper(i);
    }

    @Override
    public int addTemper(int i) {
        return getMinecraftEntity().modifyTemper(i);
    }

    @Override
    public int getMaxTemper() {
        return getMinecraftEntity().getMaxTemper();
    }

    @Override
    public void setMaxTemper(int i) {
        getMinecraftEntity().maxTemperOverride = OptionalInt.of(i);
    }

    @Override
    public void resetMaxTemper() {
        getMinecraftEntity().maxTemperOverride = OptionalInt.empty();
    }

    @Override
    public Optional<UUID> getOwner() {
        return Optional.ofNullable(getMinecraftEntity().getOwnerUUID());
    }

    @Override
    public void setOwner(@NotNull UUID uuid) {
        getMinecraftEntity().setOwnerUUID(uuid);
    }

    @Override
    public boolean canJump() {
        return getMinecraftEntity().canJump();
    }

    @Override
    public void startJumping(int i) {
        getMinecraftEntity().handleStartJump(i);
    }

    @Override
    public void stopJumping() {
        getMinecraftEntity().handleStopJump();
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
