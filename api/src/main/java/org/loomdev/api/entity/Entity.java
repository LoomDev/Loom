package org.loomdev.api.entity;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.damage.DamageSource;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.math.BoundingBox;
import org.loomdev.api.math.Vector3d;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Entity extends CommandSource {

    @NotNull EntityType getType();

    int getEntityId();

    @NotNull UUID getUniqueId();

    @NotNull String getName();

    @NotNull Component getDisplayName();

    @Nullable Component getCustomName();

    void setCustomName(@Nullable Component component);

    boolean hasCustomName();

    boolean isCustomNameVisible();

    void setCustomNameVisible(boolean flag);

    @NotNull BoundingBox getBoundingBox();

    @NotNull Location getLocation();

    @NotNull World getWorld();

    boolean teleport(@NotNull Entity entity); // TODO add tp cause

    boolean teleport(@NotNull Location location); // TODO add tp cause

    void remove();

    void destroy();

    boolean isDead();

    @NotNull Optional<Entity> getVehicle();

    void leaveVehicle();

    boolean isOnVehicle(); // TODO find a better name.

    @NotNull List<Entity> getPassengers();

    void addPassenger(@NotNull Entity passenger);

    @NotNull Optional<Entity> getPassenger();

    void setPassenger(@NotNull Entity passenger);

    void removePassenger(@NotNull Entity passenger);

    boolean hasPassengers();

    void ejectPassengers();

    @NotNull Vector3d getVelocity();

    void setVelocity(@NotNull Vector3d velocity);

    void addVelocity(@NotNull Vector3d velocity);

    boolean isOnGround();

    boolean isSilent();

    void setSilent(boolean flag);

    boolean isGlowing();

    void setGlowing(boolean flag);

    boolean hasNoGravity();

    void setNoGravity(boolean flag);

    int getAge();

    int getPortalCooldown();

    void setPortalCooldown(int ticks);

    int getFireTicks();

    void setFireTicks(int ticks);

    void setOnFireFor(int ticks);

    boolean isOnFire();

    double getFallDistance();

    void setFallDistance(float distance);

    double getEyeY();

    boolean isSwimming();

    void setSwimming(boolean flag);

    boolean isInvisible();

    void  setInvisible(boolean flag);

    boolean isInvulnerable();

    void setInvulnerable(boolean flag);

    boolean isInvulnerableTo(DamageSource damageSource);

    void setRotation(float yaw, float pitch);

    boolean hasWings();

    void emitSound(@NotNull Sound.Type type, float volume, float pitch);

    boolean isFireResistant();

    void  setFireResistant(boolean flag);

    void resetFireResistance();

    boolean isTouchingWater();

    boolean isBeingRainedOn();

    boolean isInsideBubbleColumn();

    default boolean isTouchingRainOrWater() {
        return isTouchingWater() || isBeingRainedOn();
    }

    default boolean isWet() {
        return isTouchingWater() || isBeingRainedOn() || isInsideBubbleColumn();
    }

    default boolean isInsideWaterOrBubbleColumn() {
        return isTouchingWater() || isInsideBubbleColumn();
    }

    boolean isSubmergedInWater();

    boolean isInLava();

    boolean isSubmergedInLava();

    float getBrightnessAtEyes();

    float distanceTo(@NotNull Entity entity);

    double squaredDistanceTo(@NotNull Entity entity);

    void pushAwayFrom(@NotNull Entity entity);

    void dropStack(@NotNull ItemStack itemStack);

    void dropStack(@NotNull ItemStack itemStack, float yOffset);

    boolean isInsideWall();

    boolean canFly();

    HoverEvent<HoverEvent.ShowEntity> getHoverEvent();

    // TODO persistent api?

    // TODO Last damage?

    // TODO pose

    // TODO scoreboard stuff

    // TODO getEntitiesNearby(double radius)

}
