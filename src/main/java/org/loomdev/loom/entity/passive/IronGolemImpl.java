package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.IronGolemEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.passive.IronGolem;
import org.loomdev.api.entity.player.Player;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.entity.mob.PathAwareEntityImpl;
import org.loomdev.loom.entity.player.PlayerImpl;

import java.util.Optional;
import java.util.UUID;

public class IronGolemImpl extends PathAwareEntityImpl implements IronGolem {

    public IronGolemImpl(IronGolemEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.IRON_GOLEM;
    }

    @Override
    public IronGolemEntity getMinecraftEntity() {
        return (IronGolemEntity) super.getMinecraftEntity();
    }

    @Override
    public boolean isPlayerMade() {
        return getMinecraftEntity().isPlayerCreated();
    }

    @Override
    public void setPlayerMade(boolean flag) {
        getMinecraftEntity().setPlayerCreated(flag);
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
