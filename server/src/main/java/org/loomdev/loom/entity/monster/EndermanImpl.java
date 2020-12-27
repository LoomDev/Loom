package org.loomdev.loom.entity.monster;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.EnderMan;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.monster.Enderman;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.entity.player.PlayerImpl;

import java.util.Optional;
import java.util.UUID;

public class EndermanImpl extends MonsterImpl implements Enderman {

    public EndermanImpl(EnderMan entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Enderman> getType() {
        return EntityType.ENDERMAN;
    }

    @Override
    @NotNull
    public EnderMan getMinecraftEntity() {
        return (EnderMan) super.getMinecraftEntity();
    }

    @Override
    public boolean isProvoked() {
        return getMinecraftEntity().hasBeenStaredAt();
    }

    @Override
    public void setProvoked(boolean provoked) {
        getMinecraftEntity().entityData.set(EnderMan.DATA_STARED_AT, provoked);
    }

    @Override
    public int getAngerTime() {
        return getMinecraftEntity().getRemainingPersistentAngerTime();
    }

    @Override
    public void setAngerTime(int i) {
        getMinecraftEntity().setRemainingPersistentAngerTime(i);
    }

    @Override
    public boolean isAngry() {
        return getMinecraftEntity().isAngry();
    }

    @Override
    public void stopAnger() {
        getMinecraftEntity().forgetCurrentTargetAndRefreshUniversalAnger();
    }

    @Override
    @NotNull
    public Optional<UUID> getAngryAt() {
        return Optional.ofNullable(getMinecraftEntity().getPersistentAngerTarget());
    }

    @Override
    public void setAngryAt(@Nullable UUID uuid) {
        getMinecraftEntity().setPersistentAngerTarget(uuid);
    }

    @Override
    @NotNull
    public Optional<LivingEntity> getTarget() {
        return Optional.ofNullable(getMinecraftEntity().getTarget())
                .map(Entity::getLoomEntity)
                .map(LivingEntity.class::cast);
    }

    @Override
    public void setTarget(@Nullable LivingEntity entity) {
        if (entity == null) {
            getMinecraftEntity().setTarget(null);
            return;
        }

        getMinecraftEntity().setTarget(((LivingEntityImpl) entity).getMinecraftEntity());
    }

    @Override
    public void forgive(@NotNull Player player) {
        getMinecraftEntity().playerDied(((PlayerImpl) player).getMinecraftEntity());
    }
}
