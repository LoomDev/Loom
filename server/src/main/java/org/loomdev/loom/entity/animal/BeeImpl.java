package org.loomdev.loom.entity.animal;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.passive.Bee;
import org.loomdev.api.entity.player.Player;
import org.loomdev.api.world.Location;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.entity.player.PlayerImpl;

import java.util.Optional;
import java.util.UUID;

public class BeeImpl extends AnimalImpl implements Bee {

    public BeeImpl(net.minecraft.world.entity.animal.Bee entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Bee> getType() {
        return EntityType.BEE;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Bee getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Bee) super.getMinecraftEntity();
    }

    @Override
    public Optional<Location> getFlowerLocation() {
        // TODO world?
        return Optional.ofNullable(getMinecraftEntity().getSavedFlowerPos()).map(bp -> new Location(null, bp.getX(), bp.getY(), bp.getZ()));
    }

    @Override
    public void setFlowerLocation(@Nullable Location location) { // TODO this will break
        getMinecraftEntity().setSavedFlowerPos(new BlockPos(location.getX(), location.getY(), location.getZ()));
    }

    @Override
    public boolean hasFlower() {
        return getMinecraftEntity().hasSavedFlowerPos();
    }

    @Override
    public Optional<Location> getHiveLocation() {
        // TODO world?
        return Optional.ofNullable(getMinecraftEntity().getHivePos()).map(bp -> new Location(null, bp.getX(), bp.getY(), bp.getZ()));
    }

    @Override
    public void setHiveLocation(@Nullable Location location) {
        // TODO
    }

    @Override
    public boolean hasHive() {
        return getMinecraftEntity().hasHive();
    }

    @Override
    public boolean hasNectar() {
        return getMinecraftEntity().hasNectar();
    }

    @Override
    public void setHasNectar(boolean b) {
        getMinecraftEntity().setHasNectar(b);
    }

    @Override
    public boolean hasStung() {
        return getMinecraftEntity().hasStung();
    }

    @Override
    public void setHasStung(boolean stung) {
        getMinecraftEntity().setHasStung(stung);
    }

    @Override
    public int getTicksUntilCanPollinate() {
        return getMinecraftEntity().remainingCooldownBeforeLocatingNewFlower;
    }

    @Override
    public void setTicksUntilCanPollinate(int ticks) {
        getMinecraftEntity().remainingCooldownBeforeLocatingNewFlower = ticks;
    }

    @Override
    public int getCannotEnterHiveTicks() {
        return getMinecraftEntity().stayOutOfHiveCountdown;
    }

    @Override
    public void setCannotEnterHiveTicks(int ticks) {
        getMinecraftEntity().stayOutOfHiveCountdown = ticks;
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
        return getAngerTime() > 0;
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
