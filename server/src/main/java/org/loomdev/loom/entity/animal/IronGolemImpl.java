package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.passive.IronGolem;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.entity.player.PlayerImpl;

import java.util.Optional;
import java.util.UUID;

public class IronGolemImpl extends AbstractGolemImpl implements IronGolem {

    public IronGolemImpl(net.minecraft.world.entity.animal.IronGolem entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<IronGolem> getType() {
        return EntityType.IRON_GOLEM;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.IronGolem getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.IronGolem) super.getMinecraftEntity();
    }

    @Override
    public boolean isPlayerCreated() {
        return getMinecraftEntity().isPlayerCreated();
    }

    @Override
    public void setPlayerCreated(boolean flag) {
        getMinecraftEntity().setPlayerCreated(flag);
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
        return (LivingEntityImpl) target.getLoomEntity();
    }

    @Override
    public void setTarget(@NotNull LivingEntity livingEntity) {
        getMinecraftEntity().setTarget(((LivingEntityImpl) livingEntity).getMinecraftEntity());
    }

    @Override
    public void forgive(@NotNull Player player) {
        getMinecraftEntity().playerDied(((PlayerImpl) player).getMinecraftEntity());
    }
}
