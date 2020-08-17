package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.PolarBearEntity;
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

public class PolarBearImpl extends AnimalEntityImpl implements PolarBear {

    public PolarBearImpl(PolarBearEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.POLAR_BEAR;
    }

    @Override
    public PolarBearEntity getMinecraftEntity() {
        return (PolarBearEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isWarning() {
        return getMinecraftEntity().isWarning();
    }

    @Override
    public void setWarning(boolean flag) {
        getMinecraftEntity().setWarning(flag);
    }

    @Override
    public int getAngerTime() {
        return getMinecraftEntity().getAngerTime();
    }

    @Override
    public void setAngerTime(int ticks) {
        getMinecraftEntity().setAngerTime(ticks);
    }

    @Override
    public boolean isAngry() {
        return getMinecraftEntity().getAngryAt() != null;
    }

    @Override
    public void stopAnger() {
        getMinecraftEntity().stopAnger();
    }

    @Override
    public Optional<UUID> getAngryAt() {
        return Optional.ofNullable(getMinecraftEntity().getAngryAt());
    }

    @Override
    public void setAngryAt(@Nullable UUID uuid) {
        getMinecraftEntity().setAngryAt(uuid);
    }

    @Override
    public Optional<LivingEntity> getTarget() {
        return Optional.ofNullable((LivingEntity) getMinecraftEntity().getTarget().getLoomEntity());
    }

    @Override
    public void setTarget(@NotNull LivingEntity livingEntity) {
        getMinecraftEntity().setTarget(((LivingEntityImpl) livingEntity).getMinecraftEntity());
    }

    @Override
    public void forgive(@NotNull Player player) {
        getMinecraftEntity().forgive(((PlayerImpl) player).getMinecraftEntity());
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
