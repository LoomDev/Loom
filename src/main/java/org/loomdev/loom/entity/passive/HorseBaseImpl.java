package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.sound.SoundCategory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.passive.HorseBase;
import org.loomdev.api.sound.SoundCategories;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

public class HorseBaseImpl extends AnimalEntityImpl implements HorseBase {

    public HorseBaseImpl(HorseBaseEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull HorseBaseEntity getMinecraftEntity() {
        return (HorseBaseEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isInAir() {
        return getMinecraftEntity().isInAir();
    }

    @Override
    public void setInAir(boolean b) {
        getMinecraftEntity().setInAir(b);
    }

    @Override
    public boolean isTame() {
        return getMinecraftEntity().isTame();
    }

    @Override
    public void setTame(boolean b) {
        getMinecraftEntity().setTame(b);
    }

    @Override
    public boolean isEatingGrass() {
        return getMinecraftEntity().isEatingGrass();
    }

    @Override
    public boolean isAngry() {
        return getMinecraftEntity().isAngry();
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
        return getMinecraftEntity().addTemper(i);
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
        return Optional.ofNullable(getMinecraftEntity().getOwnerUuid());
    }

    @Override
    public void setOwner(@NotNull UUID uuid) {
        getMinecraftEntity().setOwnerUuid(uuid);
    }

    @Override
    public boolean canJump() {
        return getMinecraftEntity().canJump();
    }

    @Override
    public void startJumping(int i) {
        getMinecraftEntity().startJumping(i);
    }

    @Override
    public void stopJumping() {
        getMinecraftEntity().stopJumping();
    }

    @Override
    public boolean canBeSaddled() {
        return getMinecraftEntity().canBeSaddled();
    }

    @Override
    public void saddle(@Nullable SoundCategories soundCategories) {
        getMinecraftEntity().saddle(SoundCategory.valueOf(soundCategories.name()));
    }

    @Override
    public boolean isSaddled() {
        return getMinecraftEntity().isSaddled();
    }
}
