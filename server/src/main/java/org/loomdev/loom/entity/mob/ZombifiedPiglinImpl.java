package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.mob.ZombifiedPiglin;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.entity.player.PlayerImpl;

import java.util.Optional;
import java.util.UUID;

public class ZombifiedPiglinImpl extends ZombieImpl implements ZombifiedPiglin {

    public ZombifiedPiglinImpl(ZombifiedPiglinEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ZOMBIFIED_PIGLIN;
    }

    @Override
    public @NotNull ZombifiedPiglinEntity getMinecraftEntity() {
        return (ZombifiedPiglinEntity) super.getMinecraftEntity();
    }

    @Override
    public int getAngerTime() {
        return getMinecraftEntity().getAngerTime();
    }

    @Override
    public void setAngerTime(int time) {
        getMinecraftEntity().setAngerTime(time);
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
        return Optional.ofNullable(getMinecraftEntity().getTarget()).map(e -> (LivingEntity) e.getLoomEntity());
    }

    @Override
    public void setTarget(@NotNull LivingEntity livingEntity) {
        getMinecraftEntity().setTarget(((LivingEntityImpl) livingEntity).getMinecraftEntity());
    }

    @Override
    public void forgive(@NotNull Player player) {
        getMinecraftEntity().forgive(((PlayerImpl) player).getMinecraftEntity());
    }
}
