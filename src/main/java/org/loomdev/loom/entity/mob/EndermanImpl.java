package org.loomdev.loom.entity.mob;

import net.minecraft.entity.mob.EndermanEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.mob.Enderman;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.entity.player.PlayerImpl;

import java.util.Optional;
import java.util.UUID;

public class EndermanImpl extends HostileEntityImpl implements Enderman {

    public EndermanImpl(EndermanEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ENDERMAN;
    }

    @Override
    public EndermanEntity getMinecraftEntity() {
        return (EndermanEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isProvoked() {
        return getMinecraftEntity().isProvoked();
    }

    @Override
    public void setProvoked(boolean b) {
        getMinecraftEntity().dataTracker.set(EndermanEntity.PROVOKED, true);
    }

    @Override
    public int getAngerTime() {
        return getMinecraftEntity().getAngerTime();
    }

    @Override
    public void setAngerTime(int i) {
        getMinecraftEntity().setAngerTime(i);
    }

    @Override
    public boolean isAngry() {
        return getMinecraftEntity().isAngry();
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
        return Optional.ofNullable(getMinecraftEntity().getTarget()).map(e -> (LivingEntityImpl) e.getLoomEntity());
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
