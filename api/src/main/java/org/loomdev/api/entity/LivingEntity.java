package org.loomdev.api.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.effect.StatusEffect;
import org.loomdev.api.entity.effect.StatusEffectType;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.util.EquipmentSlot;
import org.loomdev.api.util.Hand;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Represents a living entity.
 * A living entity has health, can have {@link StatusEffect}s, can drown, and become invisible.
 */
public interface LivingEntity extends Damageable {

    @NotNull List<StatusEffect> getStatusEffects();

    @NotNull
    Optional<StatusEffect> getStatusEffect(@NotNull StatusEffectType type);

    void addStatusEffect(@NotNull StatusEffect effect);

    void removeStatusEffect(@NotNull StatusEffectType type);

    void clearStatusEffects();

    boolean hasStatusEffect(@NotNull StatusEffectType type);

    int getAirSupply();

    void setAirSupply(int ticks);

    int getMaxAirSupply();

    void setMaxAirSupply(int ticks);

    void resetMaxAir();

    int getNoDamageTicks();

    void setNoDamageTicks(int ticks);

    void swingHand(@NotNull Hand hand);

    int getRiptideTicks();

    void setRiptideTicks(int ticks);

    boolean isUsingRiptide();

    boolean isSleeping();

    boolean hasLineOfSight(@NotNull Entity other);

    boolean canPickupItems();

    void setCanPickItems(boolean flag);

    int getStuckArrowCount();

    void setStuckArrowCount(int count);

    int getStingerCount();

    void setStringerCount(int count);

    boolean isHolding(@NotNull ItemType itemType);

    boolean isHolding(@NotNull Predicate<ItemType> predicate);

    @NotNull
    Optional<ItemStack> getEquipment(@NotNull EquipmentSlot slot);

    void setEquipment(@NotNull EquipmentSlot slot, @NotNull ItemStack equipment);

    float getMovementSpeed();

    void setMovementSpeed(float speed);

    boolean isSensitiveToWater();

    void setSensitiveToWater(boolean flag);

    void resetSensitiveToWater();

    boolean canSee(@NotNull Entity entity);

    float getHeadYaw();

    void setHeadYaw(float yaw);

    float getBodyYaw();

    void setBodyYaw(float yaw);

    boolean isUsingItem();

    @NotNull
    Hand getActiveHand();

    void setActiveHand(@NotNull Hand hand);

    boolean isGliding();

    Optional<Location> getSleepingPosition();

    void setSleepingPosition(Location location);

    void clearSleepingPosition();

    void wakeUp();

    ItemStack eatFood(World world, ItemStack itemStack);

    // TODO attributes

    // TODO colidable enable/disable

    // TODO equipment

}
