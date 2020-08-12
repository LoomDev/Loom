package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.math.BlockPos;
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

public class BeeImpl extends AnimalEntityImpl implements Bee {
    public BeeImpl(AnimalEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.BEE;
    }

    @Override
    public BeeEntity getMinecraftEntity() {
        return (BeeEntity) super.getMinecraftEntity();
    }

    @Override
    public Optional<Location> getFlowerLocation() {
        // TODO world?
        return Optional.ofNullable(getMinecraftEntity().getFlowerPos()).map(bp -> new Location(null, bp.getX(), bp.getY(), bp.getZ()));
    }

    @Override
    public void setFlowerLocation(@Nullable Location location) { // TODO this will break
        getMinecraftEntity().setFlowerPos(new BlockPos(location.getX(), location.getY(), location.getZ()));
    }

    @Override
    public boolean hasFlower() {
        return getMinecraftEntity().hasFlower();
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
        return getMinecraftEntity().ticksUntilCanPollinate;
    }

    @Override
    public void setTicksUntilCanPollinate(int ticks) {
        getMinecraftEntity().ticksUntilCanPollinate = ticks;
    }

    @Override
    public int getCannotEnterHiveTicks() {
        return getMinecraftEntity().cannotEnterHiveTicks;
    }

    @Override
    public void setCannotEnterHiveTicks(int ticks) {
        getMinecraftEntity().cannotEnterHiveTicks = ticks;
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
        return getAngerTime() > 0;
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
        return Optional.ofNullable( getMinecraftEntity().getTarget()).map(e -> (LivingEntityImpl) e.getLoomEntity());
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
