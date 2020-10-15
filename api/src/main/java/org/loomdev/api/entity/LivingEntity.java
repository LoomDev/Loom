package org.loomdev.api.entity;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.effect.StatusEffect;
import org.loomdev.api.entity.effect.StatusEffectType;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.util.Hand;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface LivingEntity extends Damageable {

    @NotNull List<StatusEffect> getStatusEffects();

    @NotNull Optional<StatusEffect> getStatusEffect(@NotNull StatusEffectType type);

    void addStatusEffect(@NotNull StatusEffect effect);

    void removeStatusEffect(@NotNull StatusEffectType type);

    void clearStatusEffects();

    boolean hasStatusEffect(@NotNull StatusEffectType type);

    int getAir();

    void setAir(int ticks);

    int getMaxAir();

    void setMaxAir(int ticks);

    void resetMaxAir();

    int getNoDamageTicks();

    void setNoDamageTicks(int ticks);

    void swingHand(@NotNull Hand hand);

    boolean isUsingRiptide();

    boolean isSleeping();

    boolean hasLineOfSight(@NotNull Entity other);

    boolean canPickupItems();

    void setCanPickItems(boolean flag);

    boolean isAlive();

    int getStuckArrowCount();

    void setStuckArrowCount(int count);

    int getStingerCount();

    void setStringerCount(int count);

    boolean isHolding(@NotNull ItemType itemType);

    boolean isHolding(@NotNull Predicate<ItemType> predicate);

    @NotNull Optional<ItemStack> getItemInHand(@NotNull Hand hand);

    void setItemInHand(@NotNull Hand hand, @NotNull ItemStack itemStack);

    @NotNull Optional<ItemStack> getBoots();

    void setBoots(@NotNull ItemStack itemStack);

    @NotNull Optional<ItemStack> getLeggings();

    void setLeggings(@NotNull ItemStack itemStack);

    @NotNull Optional<ItemStack> getChestplate();

    void setChestplate(@NotNull ItemStack itemStack);

    @NotNull Optional<ItemStack> getHelmet();

    void  setHelmet(@NotNull ItemStack itemStack);

    float getMovementSpeed();

    void setMovementSpeed(float speed);

    boolean isHurtByWater();

    void setHurtByWater(boolean flag);

    void resetHurtByWater();

    boolean canSee(@NotNull Entity entity);

    float getHeadYaw();

    void setHeadYaw(float yaw);

    float getBodyYaw();

    void setBodyYaw(float yaw);

    boolean isUsingItem();

    @NotNull Hand getActiveHand();

    void setCurrentHand(Hand hand);

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
