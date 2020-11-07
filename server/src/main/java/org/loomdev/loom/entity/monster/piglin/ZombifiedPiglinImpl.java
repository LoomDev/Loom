package org.loomdev.loom.entity.monster.piglin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.monster.hoglin.ZombifiedPiglin;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.entity.monster.zombie.ZombieImpl;
import org.loomdev.loom.entity.player.PlayerImpl;

import java.util.UUID;

public class ZombifiedPiglinImpl extends ZombieImpl implements ZombifiedPiglin {

    public ZombifiedPiglinImpl(net.minecraft.world.entity.monster.ZombifiedPiglin entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<ZombifiedPiglin> getType() {
        return EntityType.ZOMBIFIED_PIGLIN;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.monster.ZombifiedPiglin getMinecraftEntity() {
        return (net.minecraft.world.entity.monster.ZombifiedPiglin) super.getMinecraftEntity();
    }

    @Override
    public int getAngerTime() {
        return getMinecraftEntity().getRemainingPersistentAngerTime();
    }

    @Override
    public void setAngerTime(int time) {
        getMinecraftEntity().setRemainingPersistentAngerTime(time);
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
}
