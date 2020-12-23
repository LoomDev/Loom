package org.loomdev.api.entity;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.command.CommandSource;
import org.loomdev.api.entity.damage.DamageSource;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.math.BoundingBox;
import org.loomdev.api.math.vector.Vector3d;
import org.loomdev.api.sound.SoundEvent;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;

import java.util.List;
import java.util.UUID;

public interface Entity extends CommandSource {

    /**
     * Gets the entity's type.
     *
     * @return The type.
     */
    @NotNull
    EntityType<?> getType();

    int getId();

    @NotNull
    UUID getUUID();

    /**
     * Gets the entity's name as a text component.
     *
     * @return The name.
     */
    @NotNull
    Component getName();

    @NotNull
    Component getDisplayName();

    @Nullable
    Component getCustomName();

    void setCustomName(@Nullable Component component);

    boolean hasCustomName();

    boolean isCustomNameVisible();

    void setCustomNameVisible(boolean visible);

    @NotNull
    BoundingBox getBoundingBox();

    @NotNull
    Location getLocation();

    @NotNull
    World getWorld();

    boolean teleport(@NotNull Entity entity); // TODO add tp cause

    boolean teleport(@NotNull Location location); // TODO add tp cause

    void kill();

    void remove(@NotNull RemovalReason reason);

    boolean isAlive();

    @Nullable
    Entity getVehicle();

    void leaveVehicle();

    boolean hasVehicle();

    boolean isVehicle();

    @NotNull List<Entity> getPassengers();

    void addPassenger(@NotNull Entity passenger);

    void removePassenger(@NotNull Entity passenger);

    boolean hasPassengers();

    void ejectPassengers();

    @NotNull
    Vector3d getVelocity();

    void addVelocity(@NotNull Vector3d vector);

    void addVelocity(double x, double y, double z);

    void setVelocity(@NotNull Vector3d vector);

    void setVelocity(double x, double y, double z);

    boolean isOnGround();

    boolean isSilent();

    void setSilent(boolean silent);

    boolean isGlowing();

    void setGlowing(boolean glowing);

    boolean isAffectedByGravity();

    void setAffectedByGravity(boolean gravity);

    int getAge();

    int getPortalCooldown();

    void setPortalCooldown(int ticks);

    int getFireTicks();

    void setFireTicks(int ticks);

    boolean isOnFire();

    double getFallDistance();

    void setFallDistance(float distance);

    double getEyeHeight();

    boolean isSwimming();

    void setSwimming(boolean swimming);

    boolean isInvisible();

    void setInvisible(boolean invisible);

    boolean isInvulnerable();

    void setInvulnerable(boolean invulnerable);

    boolean isInvulnerableTo(DamageSource damageSource);

    void setRotation(float pitch, float yaw);

    /**
     * Gets whether the entity has wings.
     *
     * @return Whether the entity has wings.
     */
    boolean hasWings();

    void emitSound(@NotNull SoundEvent type, float volume, float pitch);

    boolean isFireResistant();

    void setFireResistant(boolean resistant);

    void resetFireResistance();

    /**
     * Gets whether the entity is touching water (does not include rain).
     *
     * @return Whether the entity is touching water.
     */
    boolean isTouchingWater();

    /**
     * Gets whether the entity is being rained on.
     *
     * @return Whether the entity is being rained on.
     */
    boolean isBeingRainedOn();

    /**
     * Gets whether the entity is in a bubble column.
     *
     * @return Whether the entity is in a bubble column.
     */
    boolean isInsideBubbleColumn();

    /**
     * Gets whether the entity is touching rain or water.
     *
     * @return Whether the entity is touching rain or water.
     */
    default boolean isTouchingRainOrWater() {
        return isTouchingWater() || isBeingRainedOn();
    }

    /**
     * Gets whether the entity is wet.
     *
     * @return Whether the entity is touching water, being rained on or inside a bubble column.
     */
    default boolean isWet() {
        return isTouchingWater() || isBeingRainedOn() || isInsideBubbleColumn();
    }

    /*
     * Gets whether the entity is inside water or a bubble column.
     *
     * @return Whether the entity is inside water or a bubble column.
     */
    default boolean isInsideWaterOrBubbleColumn() {
        return isTouchingWater() || isInsideBubbleColumn();
    }

    boolean isSubmergedInWater();

    boolean isInLava();

    boolean isSubmergedInLava();

    boolean isInsidePowderSnow();

    void setInsidePowderSnow(boolean insidePowderSnow);

    float getBrightnessAtEyes();

    float distanceTo(@NotNull Entity entity);

    double squaredDistanceTo(@NotNull Entity entity);

    void pushAwayFrom(@NotNull Entity entity);

    void dropItem(@NotNull ItemStack item);

    void dropItem(@NotNull ItemStack item, float yOffset);

    boolean isInsideWall();

    boolean canFly();

    HoverEvent<HoverEvent.ShowEntity> getHoverEvent();

    // TODO persistent api?

    // TODO Last damage?

    // TODO pose

    // TODO scoreboard stuff

    // TODO getEntitiesNearby(double radius)

    enum RemovalReason {

        /**
         * Generic removal reason, likely because the entity is no longer necessary.
         */
        DISCARDED,

        /**
         * When the entity is explicitly killed.
         */
        KILLED,

        /**
         * When an chunk unloads and the entity is saved to disk.
         */
        UNLOADED_TO_CHUNK,

        /**
         * When a player disconnects from the server while riding an entity
         * and the entity is also removed from the world.
         */
        UNLOADED_WITH_PLAYER,

        /**
         * When the entity moves into another dimension. Internally, a second entity
         * with the same attributes is created, with the old entity being removed
         * for this reason.
         */
        CHANGED_DIMENSION;
    }
}
