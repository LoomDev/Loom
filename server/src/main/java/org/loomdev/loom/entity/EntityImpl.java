package org.loomdev.loom.entity;

import com.google.common.base.Preconditions;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.damage.DamageSource;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.math.BoundingBox;
import org.loomdev.api.math.Vector3d;
import org.loomdev.api.sound.SoundEvent;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;
import org.loomdev.loom.util.transformer.TextTransformer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class EntityImpl implements Entity {

    private final net.minecraft.world.entity.Entity mcEntity;

    public EntityImpl(net.minecraft.world.entity.Entity entity) {
        mcEntity = entity;
    }

    @NotNull
    public net.minecraft.world.entity.Entity getMinecraftEntity() {
        return mcEntity;
    }

    @Override
    public int getEntityId() {
        return mcEntity.getId();
    }

    @Override
    @NotNull
    public UUID getUniqueId() {
        return mcEntity.getUUID();
    }

    @Override
    @NotNull
    public String getName() {
        return mcEntity.getName().getString(); // TODO check
    }

    @Override
    @NotNull
    public Component getDisplayName() {
        return TextTransformer.toLoom(mcEntity.getDisplayName()); // TODO check
    }

    @Nullable
    @Override
    public Component getCustomName() {
        return mcEntity.getCustomName() == null ? null : TextTransformer.toLoom(mcEntity.getCustomName());
    }

    @Override
    public void setCustomName(@Nullable Component component) {
        mcEntity.setCustomName(component == null ? null : TextTransformer.toMinecraft(component));
    }

    @Override
    public boolean hasCustomName() {
        return mcEntity.hasCustomName();
    }

    @Override
    public boolean isCustomNameVisible() {
        return mcEntity.isCustomNameVisible();
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        mcEntity.setCustomNameVisible(flag);
    }

    @Override
    @NotNull
    public BoundingBox getBoundingBox() {
        AABB mcBox = mcEntity.getBoundingBox();
        return new BoundingBox(mcBox.minX, mcBox.minY, mcBox.minZ, mcBox.maxX, mcBox.maxY, mcBox.maxZ);
    }

    @Override
    @NotNull
    public Location getLocation() {
        return new Location(getWorld(), mcEntity.getX(), mcEntity.getY(), mcEntity.getZ(), 0, 0); // TODO yaw and pitch
    }

    @Override
    @NotNull
    public World getWorld() {
        return Loom.getServer().getWorld(((ServerLevel) mcEntity.level).serverLevelData.getLevelName()); // TODO use UUID, check null
    }

    @Override
    public boolean teleport(@NotNull Entity entity) {
        return this.teleport(entity.getLocation());
    }

    @Override
    public boolean teleport(@NotNull Location location) {
        Preconditions.checkNotNull(location);

        if (mcEntity.isVehicle() || mcEntity.removalReason != null) {
            return false;
        }

        this.mcEntity.stopRiding();

        if (!location.getWorld().equals(this.getWorld())) {
            // mcEntity.changeDimension(world, blockpos) ?
            // TODO change dimensions
            return true;
        }

        mcEntity.moveTo(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        mcEntity.setYHeadRot(location.getYaw());
        return true;
    }

    @Override
    public void remove() {
        mcEntity.outOfWorld();
    }

    @Override
    public void destroy() {
        getMinecraftEntity().outOfWorld();
    }

    @Override
    public boolean isDead() {
        return !mcEntity.isAlive();
    }

    @Override
    @NotNull
    public Optional<Entity> getVehicle() {
        return Optional.ofNullable(this.mcEntity.getVehicle()).map(net.minecraft.world.entity.Entity::getLoomEntity);
    }

    @Override
    public void leaveVehicle() {
        this.mcEntity.stopRiding();
    }

    @Override
    public boolean isOnVehicle() {
        return mcEntity.isPassenger();
    }

    @Override
    @NotNull
    public List<Entity> getPassengers() {
        return mcEntity.getPassengers().stream().map(net.minecraft.world.entity.Entity::getLoomEntity).collect(Collectors.toList());
    }

    @Override
    public void addPassenger(@NotNull Entity entity) {
        Preconditions.checkArgument(!this.equals(entity), "Entities cannot ride itself.");
        ((EntityImpl) entity).getMinecraftEntity().startRiding(getMinecraftEntity(), true);
    }

    @Override
    @NotNull
    public Optional<Entity> getPassenger() {
        if (!hasPassengers()) {
            return Optional.empty();
        }
        return Optional.of(this.mcEntity.getPassengers().get(0).getLoomEntity());
    }

    @Override
    public void setPassenger(@NotNull Entity entity) {
        Preconditions.checkArgument(!this.equals(entity), "Entities cannot ride itself.");
        if (entity instanceof EntityImpl) {
            ejectPassengers();
            ((EntityImpl) entity).getMinecraftEntity().startRiding(getMinecraftEntity());
        }
    }

    @Override
    public void removePassenger(@NotNull Entity entity) {
        Preconditions.checkNotNull(entity);
        ((EntityImpl) entity).getMinecraftEntity().stopRiding();
    }

    @Override
    public boolean hasPassengers() {
        return mcEntity.isVehicle();
    }

    @Override
    public void ejectPassengers() {
        if (this.hasPassengers()) {
            mcEntity.ejectPassengers();
        }
    }

    @Override
    @NotNull
    public Vector3d getVelocity() {
        var vec = mcEntity.getDeltaMovement();
        return new Vector3d(vec.x, vec.y, vec.z);
    }

    @Override
    public void setVelocity(@NotNull Vector3d vec) {
        mcEntity.setDeltaMovement(new Vec3(vec.getX(), vec.getY(), vec.getZ()));
    }

    @Override
    public void addVelocity(@NotNull Vector3d vec) {
        getMinecraftEntity().setDeltaMovement(getMinecraftEntity().getDeltaMovement().add(vec.getX(), vec.getY(), vec.getZ()));
    }

    @Override
    public boolean isOnGround() {
        return mcEntity.isOnGround();
    }

    @Override
    public boolean isSilent() {
        return mcEntity.isSilent();
    }

    @Override
    public void setSilent(boolean flag) {
        mcEntity.setSilent(flag);
    }

    @Override
    public boolean isGlowing() {
        return mcEntity.isGlowing();
    }

    @Override
    public void setGlowing(boolean flag) {
        mcEntity.setGlowing(flag);
    }

    @Override
    public boolean hasNoGravity() {
        return mcEntity.isNoGravity();
    }

    @Override
    public void setNoGravity(boolean flag) {
        mcEntity.setNoGravity(flag);
    }

    @Override
    public int getAge() {
        return this.mcEntity.tickCount;
    }

    @Override
    public int getPortalCooldown() {
        return mcEntity.portalCooldown;
    }

    @Override
    public void setPortalCooldown(int ticks) {
        this.mcEntity.portalCooldown = ticks;
    }

    @Override
    public int getFireTicks() {
        return mcEntity.getRemainingFireTicks();
    }

    @Override
    public void setFireTicks(int ticks) {
        mcEntity.setRemainingFireTicks(ticks);
    }

    @Override
    public void setOnFireFor(int ticks) { // TOOD prolly doesn't work as intended
        mcEntity.setSecondsOnFire(ticks);
    }

    @Override
    public boolean isOnFire() {
        return getMinecraftEntity().isOnFire();
    }

    @Override
    public double getFallDistance() {
        return mcEntity.fallDistance;
    }

    @Override
    public void setFallDistance(float distance) {
        mcEntity.fallDistance = distance;
    }

    @Override
    public double getEyeY() {
        return mcEntity.getEyeY();
    }

    @Override
    public boolean isSwimming() {
        return mcEntity.isSwimming();
    }

    @Override
    public void setSwimming(boolean flag) {
        mcEntity.setSwimming(flag);
    }

    @Override
    public boolean isInvisible() {
        return mcEntity.isInvisible();
    }

    @Override
    public void setInvisible(boolean flag) {
        mcEntity.setInvisible(flag);
    }

    @Override
    public boolean isInvulnerable() {
        return mcEntity.isInvulnerable();
    }

    @Override
    public void setInvulnerable(boolean flag) {
        mcEntity.setInvulnerable(flag);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return false; // TODO
    }

    @Override
    public void setRotation(float v, float v1) {
        // TODO
    }

    @Override
    public boolean hasWings() {
        return getMinecraftEntity().makeFlySound();
    }

    @Override
    public void emitSound(@NotNull SoundEvent sound, float volume, float pitch) {
        getMinecraftEntity().playSound(Registry.SOUND_EVENT.get(new ResourceLocation(sound.getKey().toString())), volume, pitch);
    }

    @Override
    public boolean isFireResistant() {
        return getMinecraftEntity().fireImmune();
    }

    @Override
    public void setFireResistant(boolean flag) {
        this.getMinecraftEntity().fireResistantOverride = Optional.of(flag);
    }

    @Override
    public void resetFireResistance() {
        getMinecraftEntity().fireResistantOverride = Optional.empty();
    }

    @Override
    public boolean isTouchingWater() {
        return getMinecraftEntity().isInWater();
    }

    @Override
    public boolean isBeingRainedOn() {
        return getMinecraftEntity().isInWaterOrRain();
    }

    @Override
    public boolean isInsideBubbleColumn() {
        return getMinecraftEntity().isInWaterOrBubble();
    }

    @Override
    public boolean isSubmergedInWater() {
        return getMinecraftEntity().isUnderWater();
    }

    @Override
    public boolean isInLava() {
        return getMinecraftEntity().isInLava();
    }

    @Override
    public boolean isSubmergedInLava() {
        return getMinecraftEntity().isEyeInFluid(FluidTags.LAVA);
    }

    @Override
    public float getBrightnessAtEyes() {
        return getMinecraftEntity().getBrightness();
    }

    @Override
    public float distanceTo(@NotNull Entity entity) {
        return getMinecraftEntity().distanceTo(((EntityImpl) entity).getMinecraftEntity());
    }

    @Override
    public double squaredDistanceTo(@NotNull Entity entity) {
        return getMinecraftEntity().distanceToSqr(((EntityImpl) entity).getMinecraftEntity());
    }

    @Override
    public void pushAwayFrom(@NotNull Entity entity) {
        getMinecraftEntity().push(((EntityImpl) entity).getMinecraftEntity());
    }

    @Override
    public void dropStack(@NotNull ItemStack itemStack) {
        // getMinecraftEntity().dropStack(null); // TODO transform
    }

    @Override
    public void dropStack(@NotNull ItemStack itemStack, float yOffset) {
        // getMinecraftEntity().dropStack(null, yOffset); // TODO transform
    }

    @Override
    public boolean isInsideWall() {
        return getMinecraftEntity().isInWall();
    }

    @Override
    public boolean canFly() {
        return getMinecraftEntity().isPushedByFluid();
    }

    @Override
    public HoverEvent<HoverEvent.ShowEntity> getHoverEvent() {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public void sendMessage(@NotNull String s) {
    }

    @Override
    public void sendMessage(@NotNull Component component) {
    }
}
