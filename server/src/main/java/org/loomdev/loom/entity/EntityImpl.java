package org.loomdev.loom.entity;

import com.google.common.base.Preconditions;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.Loom;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.damage.DamageSource;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.math.BoundingBox;
import org.loomdev.api.math.vector.Vector3d;
import org.loomdev.api.sound.SoundEvent;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;
import org.loomdev.loom.command.CommandSourceImpl;
import org.loomdev.loom.item.ItemStackImpl;
import org.loomdev.loom.util.transformer.TextTransformer;
import org.loomdev.loom.world.WorldImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class EntityImpl extends CommandSourceImpl implements Entity {

    private final net.minecraft.world.entity.Entity mcEntity;

    public EntityImpl(net.minecraft.world.entity.Entity entity) {
        super(entity);
        this.mcEntity = entity;
    }

    @NotNull
    public net.minecraft.world.entity.Entity getMinecraftEntity() {
        return mcEntity;
    }

    @Override
    public int getId() {
        return getMinecraftEntity().getId();
    }

    @Override
    @NotNull
    public UUID getUUID() {
        return getMinecraftEntity().getUUID();
    }

    @Override
    @NotNull
    public Component getName() {
        return TextTransformer.toLoom(getMinecraftEntity().getName());
    }

    @Override
    @NotNull
    public Component getDisplayName() {
        return TextTransformer.toLoom(getMinecraftEntity().getDisplayName());
    }

    @Override
    @Nullable
    public Component getCustomName() {
        return getMinecraftEntity().getCustomName() == null ? null : TextTransformer.toLoom(getMinecraftEntity().getCustomName());
    }

    @Override
    public void setCustomName(@Nullable Component component) {
        getMinecraftEntity().setCustomName(component == null ? null : TextTransformer.toMinecraft(component));
    }

    @Override
    public boolean hasCustomName() {
        return getMinecraftEntity().hasCustomName();
    }

    @Override
    public boolean isCustomNameVisible() {
        return getMinecraftEntity().isCustomNameVisible();
    }

    @Override
    public void setCustomNameVisible(boolean visible) {
        getMinecraftEntity().setCustomNameVisible(visible);
    }

    @Override
    @NotNull
    public BoundingBox getBoundingBox() {
        var box = getMinecraftEntity().getBoundingBox();
        return new BoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
    }

    @Override
    @NotNull
    public Location getLocation() {
        return new Location(
                getWorld(),
                getMinecraftEntity().getX(),
                getMinecraftEntity().getY(),
                getMinecraftEntity().getZ(),
                getMinecraftEntity().xRot,
                getMinecraftEntity().yRot
        );
    }

    @Override
    @NotNull
    public World getWorld() {
        return ((ServerLevel) getMinecraftEntity().level).getLoomWorld();
    }

    @Override
    public boolean teleport(@NotNull Entity entity) {
        return teleport(entity.getLocation());
    }

    @Override
    public boolean teleport(@NotNull Location position) {
        if (!isAlive()) {
            return false;
        }

        if (getMinecraftEntity().isVehicle()) {
            getMinecraftEntity().ejectPassengers();
        }

        if (getMinecraftEntity().isPassenger()) {
            getMinecraftEntity().stopRiding();
        }

        var targetWorld = position.getWorld();
        if (!targetWorld.equals(getWorld())) {
            getMinecraftEntity().changeDimension((ServerLevel) ((WorldImpl) targetWorld).getMinecraftWorld());
        }

        getMinecraftEntity().moveTo(position.getX(), position.getY(), position.getZ(), position.getYaw(), position.getPitch());
        return true;
    }

    @Override
    public void kill() {
        remove(RemovalReason.KILLED);
    }

    @Override
    public void remove(@NotNull RemovalReason reason) {
        mcEntity.remove(net.minecraft.world.entity.Entity.RemovalReason.valueOf(reason.name()));
    }

    @Override
    public boolean isAlive() {
        return getMinecraftEntity().isAlive();
    }

    @Override
    @Nullable
    public Entity getVehicle() {
        var vehicle = getMinecraftEntity().getVehicle();
        if (vehicle == null) {
            return null;
        }

        return vehicle.getLoomEntity();
    }

    @Override
    public void leaveVehicle() {
        this.mcEntity.stopRiding();
    }

    @Override
    public boolean hasVehicle() {
        return getMinecraftEntity().getVehicle() != null;
    }

    @Override
    public boolean isVehicle() {
        return getMinecraftEntity().getPassengers().size() != 0;
    }

    @Override
    @NotNull
    public List<Entity> getPassengers() {
        var passengers = getMinecraftEntity().getPassengers();
        if (passengers.size() == 0) {
            return Collections.emptyList();
        }

        var loomPassengers = new ArrayList<Entity>();
        for (var passenger : passengers) {
            loomPassengers.add(passenger.getLoomEntity());
        }

        return loomPassengers;
    }

    @Override
    public void addPassenger(@NotNull Entity entity) {
        Preconditions.checkArgument(!this.equals(entity), "Entities cannot ride themselves.");
        ((EntityImpl) entity).getMinecraftEntity().startRiding(getMinecraftEntity(), true);
    }

    @Override
    public void removePassenger(@NotNull Entity entity) {
        ((EntityImpl) entity).getMinecraftEntity().stopRiding();
    }

    @Override
    public boolean hasPassengers() {
        return isVehicle();
    }

    @Override
    public void ejectPassengers() {
        if (hasPassengers()) {
            getMinecraftEntity().ejectPassengers();
        }
    }

    @Override
    @NotNull
    public Vector3d getVelocity() {
        var velocity = getMinecraftEntity().getDeltaMovement();
        return new Vector3d(velocity.x, velocity.y, velocity.z);
    }

    @Override
    public void addVelocity(@NotNull Vector3d vector) {
        addVelocity(vector.getX(), vector.getY(), vector.getZ());
    }

    @Override
    public void addVelocity(double x, double y, double z) {
        getMinecraftEntity().setDeltaMovement(getMinecraftEntity().getDeltaMovement().add(x, y, z));
    }

    @Override
    public void setVelocity(@NotNull Vector3d vector) {
        setVelocity(vector.getX(), vector.getY(), vector.getZ());
    }

    @Override
    public void setVelocity(double x, double y, double z) {
        getMinecraftEntity().setDeltaMovement(x, y, z);
    }

    @Override
    public boolean isOnGround() {
        return getMinecraftEntity().isOnGround();
    }

    @Override
    public boolean isSilent() {
        return getMinecraftEntity().isSilent();
    }

    @Override
    public void setSilent(boolean silent) {
        getMinecraftEntity().setSilent(silent);
    }

    @Override
    public boolean isGlowing() {
        return getMinecraftEntity().isGlowing();
    }

    @Override
    public void setGlowing(boolean glowing) {
        getMinecraftEntity().setGlowing(glowing);
    }

    @Override
    public boolean isAffectedByGravity() {
        return !getMinecraftEntity().isNoGravity();
    }

    @Override
    public void setAffectedByGravity(boolean gravity) {
        getMinecraftEntity().setNoGravity(!gravity);
    }

    @Override
    public int getAge() {
        return getMinecraftEntity().tickCount;
    }

    @Override
    public int getPortalCooldown() {
        return getMinecraftEntity().portalCooldown;
    }

    @Override
    public void setPortalCooldown(int ticks) {
        getMinecraftEntity().portalCooldown = ticks;
    }

    @Override
    public int getFireTicks() {
        return getMinecraftEntity().getRemainingFireTicks();
    }

    @Override
    public void setFireTicks(int ticks) {
        getMinecraftEntity().setRemainingFireTicks(ticks);
    }

    @Override
    public boolean isOnFire() {
        return getMinecraftEntity().isOnFire();
    }

    @Override
    public double getFallDistance() {
        return getMinecraftEntity().fallDistance;
    }

    @Override
    public void setFallDistance(float distance) {
        getMinecraftEntity().fallDistance = distance;
    }

    @Override
    public double getEyeHeight() {
        return getMinecraftEntity().getEyeY();
    }

    @Override
    public boolean isSwimming() {
        return getMinecraftEntity().isSwimming();
    }

    @Override
    public void setSwimming(boolean swimming) {
        getMinecraftEntity().setSwimming(swimming);
    }

    @Override
    public boolean isInvisible() {
        return mcEntity.isInvisible();
    }

    @Override
    public void setInvisible(boolean invisible) {
        getMinecraftEntity().setInvisible(invisible);
    }

    @Override
    public boolean isInvulnerable() {
        return getMinecraftEntity().isInvulnerable();
    }

    @Override
    public void setInvulnerable(boolean invulnerable) {
        getMinecraftEntity().setInvulnerable(invulnerable);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return false; // TODO
    }

    @Override
    public void setRotation(float pitch, float yaw) {
        getMinecraftEntity().xRot = pitch;
        getMinecraftEntity().yRot = yaw;
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
        getMinecraftEntity().fireResistantOverride = Optional.of(flag);
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
    public boolean isInsidePowderSnow() {
        return getMinecraftEntity().bodyIsInPowderSnow;
    }

    @Override
    public void setInsidePowderSnow(boolean flag) {
        getMinecraftEntity().setBodyIsInPowderSnow(flag);
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
    public void dropItem(@NotNull ItemStack item) {
        dropItem(item, 0F);
    }

    @Override
    public void dropItem(@NotNull ItemStack item, float yOffset) {
        getMinecraftEntity().spawnAtLocation(((ItemStackImpl) item).getMinecraftItemStack(), yOffset);
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
