package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.passive.PolarBear;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.entity.player.PlayerImpl;

import java.util.Optional;
import java.util.UUID;

public class PolarBearImpl extends AnimalImpl implements PolarBear {

    public PolarBearImpl(net.minecraft.world.entity.animal.PolarBear entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<PolarBear> getType() {
        return EntityType.POLAR_BEAR;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.PolarBear getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.PolarBear) super.getMinecraftEntity();
    }

    @Override
    public boolean isStanding() {
        return getMinecraftEntity().isStanding();
    }

    @Override
    public void setStanding(boolean standing) {
        getMinecraftEntity().setStanding(standing);
    }

    @Override
    public int getAngerTime() {
        return getMinecraftEntity().getRemainingPersistentAngerTime();
    }

    @Override
    public void setAngerTime(int ticks) {
        getMinecraftEntity().setRemainingPersistentAngerTime(ticks);
    }

    @Override
    public boolean isAngry() {
        return getMinecraftEntity().getPersistentAngerTarget() != null;
    }

    @Override
    public void stopAnger() {
        getMinecraftEntity().forgetCurrentTargetAndRefreshUniversalAnger();
    }

    @Override
    @Nullable
    public UUID getAngryAt() {
        return getMinecraftEntity().getPersistentAngerTarget();
    }

    @Override
    public void setAngryAt(@Nullable UUID uuid) {
        getMinecraftEntity().setPersistentAngerTarget(uuid);
    }

    @Override
    @Nullable
    public LivingEntity getTarget() {
        var target = getMinecraftEntity().getTarget();
        if (target == null) return null;
        return (LivingEntity) target.getLoomEntity();
    }

    @Override
    public void setTarget(@NotNull LivingEntity livingEntity) {
        getMinecraftEntity().setTarget(((LivingEntityImpl) livingEntity).getMinecraftEntity());
    }

    @Override
    public void forgive(@NotNull Player player) {
        getMinecraftEntity().playerDied(((PlayerImpl) player).getMinecraftEntity());
    }

    @Override
    public boolean isBaby() {
        return getMinecraftEntity().isBaby();
    }

    @Override
    public void setBaby(boolean flag) {
        getMinecraftEntity().setBaby(flag);
    }
}
