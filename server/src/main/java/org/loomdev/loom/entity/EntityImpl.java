package org.loomdev.loom.entity;

import com.google.common.base.Preconditions;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.damage.DamageSource;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.math.BoundingBox;
import org.loomdev.api.math.Vector3d;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;
import org.loomdev.loom.util.transformer.TextTransformer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class EntityImpl implements Entity {

    private final net.minecraft.entity.Entity mcEntity;

    public EntityImpl(net.minecraft.entity.Entity entity) {
        mcEntity = entity;
    }

    public @NotNull net.minecraft.entity.Entity getMinecraftEntity() {
        return this.mcEntity;
    }

    @Override
    public int getEntityId() {
        return this.mcEntity.getEntityId();
    }

    @Override
    public @NotNull UUID getUniqueId() {
        return this.mcEntity.getUuid();
    }

    @Override
    public @NotNull String getName() {
        return this.mcEntity.getName().getString(); // TODO check
    }

    @Override
    public @NotNull Component getDisplayName() {
        return TextTransformer.toLoom(this.mcEntity.getDisplayName()); // TODO check
    }

    @Nullable
    @Override
    public Component getCustomName() {
        return this.mcEntity.getCustomName() == null ? null : TextTransformer.toLoom(this.mcEntity.getCustomName());
    }

    @Override
    public void setCustomName(@Nullable Component component) {
        this.mcEntity.setCustomName(component == null ? null : TextTransformer.toMinecraft(component));
    }

    @Override
    public boolean hasCustomName() {
        return this.mcEntity.hasCustomName();
    }

    @Override
    public boolean isCustomNameVisible() {
        return this.mcEntity.isCustomNameVisible();
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        this.mcEntity.setCustomNameVisible(flag);
    }

    @Override
    public @NotNull BoundingBox getBoundingBox() {
        Box mcBox = this.mcEntity.getBoundingBox();
        return new BoundingBox(mcBox.minX, mcBox.minY, mcBox.minZ, mcBox.maxX, mcBox.maxY, mcBox.maxZ);
    }

    @Override
    public @NotNull Location getLocation() {
        return new Location(getWorld(), this.mcEntity.getX(), this.mcEntity.getY(), this.mcEntity.getZ(), 0, 0); // TODO yaw and pitch
    }

    @Override
    public @NotNull World getWorld() {
        return Loom.getServer().getWorld(((ServerWorld) this.mcEntity.getEntityWorld()).worldProperties.getLevelName()).get(); // TODO use UUID
    }

    @Override
    public boolean teleport(@NotNull Entity entity) {
        return this.teleport(entity.getLocation());
    }

    @Override
    public boolean teleport(@NotNull Location location) {
        Preconditions.checkNotNull(location);

        if (this.mcEntity.hasPassengers() || this.mcEntity.removed) {
            return false;
        }

        this.mcEntity.stopRiding();

        if (!location.getWorld().equals(this.getWorld())) {
            // mcEntity.changeDimension(world, blockpos) ?
            // TODO change dimensions
            return true;
        }

        this.mcEntity.updatePositionAndAngles(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        this.mcEntity.setHeadYaw(location.getYaw());
        return true;
    }

    @Override
    public void remove() {
        this.mcEntity.remove();
    }

    @Override
    public void destroy() {
        getMinecraftEntity().destroy();
    }

    @Override
    public boolean isDead() {
        return !this.mcEntity.isAlive();
    }

    @Override
    public @NotNull Optional<Entity> getVehicle() {
        return Optional.ofNullable(this.mcEntity.getVehicle()).map(net.minecraft.entity.Entity::getLoomEntity);
    }

    @Override
    public void leaveVehicle() {
        this.mcEntity.stopRiding();
    }

    @Override
    public boolean isOnVehicle() {
        return this.mcEntity.hasVehicle();
    }

    @Override
    public @NotNull List<Entity> getPassengers() {
        return this.mcEntity.getPassengerList().stream().map(net.minecraft.entity.Entity::getLoomEntity).collect(Collectors.toList());
    }

    @Override
    public void addPassenger(@NotNull Entity entity) {
        Preconditions.checkArgument(!this.equals(entity), "Entities cannot ride itself.");
        ((EntityImpl) entity).getMinecraftEntity().startRiding(getMinecraftEntity(), true);
    }

    @Override
    public @NotNull Optional<Entity> getPassenger() {
        if (!hasPassengers()) {
            return Optional.empty();
        }
        return Optional.of(this.mcEntity.getPassengerList().get(0).getLoomEntity());
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
        return this.mcEntity.hasPassengers();
    }

    @Override
    public void ejectPassengers() {
        if (this.hasPassengers()) {
            this.mcEntity.removeAllPassengers();
        }
    }

    @Override
    public @NotNull Vector3d getVelocity() {
        Vec3d vec = this.mcEntity.getVelocity();
        return new Vector3d(vec.x, vec.y, vec.z);
    }

    @Override
    public void setVelocity(@NotNull Vector3d vec) {
        this.mcEntity.setVelocity(new Vec3d(vec.getX(), vec.getY(), vec.getZ()));
    }

    @Override
    public void addVelocity(@NotNull Vector3d vec) {
        getMinecraftEntity().addVelocity(vec.getX(), vec.getY(), vec.getZ());
    }

    @Override
    public boolean isOnGround() {
        return this.mcEntity.isOnGround();
    }

    @Override
    public boolean isSilent() {
        return this.mcEntity.isSilent();
    }

    @Override
    public void setSilent(boolean flag) {
        this.mcEntity.setSilent(flag);
    }

    @Override
    public boolean isGlowing() {
        return this.mcEntity.isGlowing();
    }

    @Override
    public void setGlowing(boolean flag) {
        this.mcEntity.setGlowing(flag);
    }

    @Override
    public boolean hasNoGravity() {
        return this.mcEntity.hasNoGravity();
    }

    @Override
    public void setNoGravity(boolean flag) {
        this.mcEntity.setNoGravity(flag);
    }

    @Override
    public int getAge() {
        return this.mcEntity.age;
    }

    @Override
    public int getPortalCooldown() {
        return this.mcEntity.netherPortalCooldown;
    }

    @Override
    public void setPortalCooldown(int ticks) {
        this.mcEntity.netherPortalCooldown = ticks;
    }

    @Override
    public int getFireTicks() {
        return this.mcEntity.getFireTicks();
    }

    @Override
    public void setFireTicks(int ticks) {
        this.mcEntity.setFireTicks(ticks);
    }

    @Override
    public void setOnFireFor(int ticks) {
        this.mcEntity.setOnFireFor(ticks);
    }

    @Override
    public boolean isOnFire() {
        return getMinecraftEntity().isOnFire();
    }

    @Override
    public double getFallDistance() {
        return this.mcEntity.fallDistance;
    }

    @Override
    public void setFallDistance(float distance) {
        this.mcEntity.fallDistance = distance;
    }

    @Override
    public double getEyeY() {
        return this.mcEntity.getEyeY();
    }

    @Override
    public boolean isSwimming() {
        return this.mcEntity.isSwimming();
    }

    @Override
    public void setSwimming(boolean flag) {
        this.mcEntity.setSwimming(flag);
    }

    @Override
    public boolean isInvisible() {
        return this.mcEntity.isInvisible();
    }

    @Override
    public void setInvisible(boolean flag) {
        this.mcEntity.setInvisible(flag);
    }

    @Override
    public boolean isInvulnerable() {
        return this.mcEntity.isInvulnerable();
    }

    @Override
    public void setInvulnerable(boolean flag) {
        this.mcEntity.setInvulnerable(flag);
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
        return getMinecraftEntity().hasWings();
    }

    @Override
    public void emitSound(@NotNull Sound.Type sound, float volume, float pitch) {
        getMinecraftEntity().playSound(Registry.SOUND_EVENT.get(sound.rawId()), volume, pitch);
    }

    @Override
    public boolean isFireResistant() {
        return getMinecraftEntity().isFireImmune();
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
        return getMinecraftEntity().isTouchingWater();
    }

    @Override
    public boolean isBeingRainedOn() {
        return getMinecraftEntity().isBeingRainedOn();
    }

    @Override
    public boolean isInsideBubbleColumn() {
        return getMinecraftEntity().isInsideBubbleColumn();
    }

    @Override
    public boolean isSubmergedInWater() {
        return getMinecraftEntity().isSubmergedInWater();
    }

    @Override
    public boolean isInLava() {
        return getMinecraftEntity().isInLava();
    }

    @Override
    public boolean isSubmergedInLava() {
        return getMinecraftEntity().isSubmergedIn(FluidTags.LAVA);
    }

    @Override
    public float getBrightnessAtEyes() {
        return getMinecraftEntity().getBrightnessAtEyes();
    }

    @Override
    public float distanceTo(@NotNull Entity entity) {
        return getMinecraftEntity().distanceTo(((EntityImpl) entity).getMinecraftEntity());
    }

    @Override
    public double squaredDistanceTo(@NotNull Entity entity) {
        return getMinecraftEntity().squaredDistanceTo(((EntityImpl) entity).getMinecraftEntity());
    }

    @Override
    public void pushAwayFrom(@NotNull Entity entity) {
        getMinecraftEntity().pushAwayFrom(((EntityImpl) entity).getMinecraftEntity());
    }

    @Override
    public void dropStack(@NotNull ItemStack itemStack) {
        getMinecraftEntity().dropStack(null); // TODO transform
    }

    @Override
    public void dropStack(@NotNull ItemStack itemStack, float yOffset) {
        getMinecraftEntity().dropStack(null, yOffset); // TODO transform
    }

    @Override
    public boolean isInsideWall() {
        return getMinecraftEntity().isInsideWall();
    }

    @Override
    public boolean canFly() {
        return getMinecraftEntity().canFly();
    }

    @Override
    public HoverEvent<HoverEvent.ShowEntity> getHoverEvent() {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public void sendMessage(@NotNull String s) { }

    @Override
    public void sendMessage(@NotNull Component component) { }
}