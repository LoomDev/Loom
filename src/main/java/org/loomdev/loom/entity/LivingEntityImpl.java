package org.loomdev.loom.entity;

import com.google.common.base.Preconditions;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.BlockPos;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.block.Material;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.effect.StatusEffect;
import org.loomdev.api.entity.effect.StatusEffectType;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.sound.Sound;
import org.loomdev.api.util.Hand;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.util.transformer.StatusEffectTransformer;
import org.loomdev.loom.util.transformer.StatusEffectTypeTransformer;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LivingEntityImpl extends EntityImpl implements LivingEntity {

    public LivingEntityImpl(net.minecraft.entity.LivingEntity entity) {
        super(entity);
    }

    @Override
    public net.minecraft.entity.LivingEntity getMinecraftEntity() {
        return (net.minecraft.entity.LivingEntity) super.getMinecraftEntity();
    }

    @Override
    public @NonNull List<StatusEffect> getStatusEffects() {
        return getMinecraftEntity().getStatusEffects().stream()
                .map(StatusEffectTransformer::toLoom)
                .collect(Collectors.toList());
    }

    @Override
    public void addStatusEffect(@NonNull StatusEffect statusEffect) {
        getMinecraftEntity().addStatusEffect(StatusEffectTransformer.toMinecraft(statusEffect));
    }

    @Override
    public @NonNull Optional<StatusEffect> getStatusEffect(@NonNull StatusEffectType statusEffectType) {
        StatusEffectInstance statusEffectInstance = getMinecraftEntity().getStatusEffect(StatusEffectTypeTransformer.toMinecraft(statusEffectType));
        return Optional.ofNullable(statusEffectInstance).map(StatusEffectTransformer::toLoom);
    }

    @Override
    public void removeStatusEffect(@NonNull StatusEffectType statusEffectType) {
        getMinecraftEntity().removeStatusEffect(StatusEffectTypeTransformer.toMinecraft(statusEffectType));
    }

    @Override
    public void clearStatusEffects() {
        getMinecraftEntity().clearStatusEffects();
    }

    @Override
    public boolean hasStatusEffect(@NonNull StatusEffectType statusEffectType) {
        return getMinecraftEntity().hasStatusEffect(StatusEffectTypeTransformer.toMinecraft(statusEffectType));
    }

    @Override
    public int getAir() {
        return getMinecraftEntity().getAir();
    }

    @Override
    public void setAir(int ticks) {
        getMinecraftEntity().setAir(ticks);
    }

    @Override
    public int getMaxAir() {
        return getMinecraftEntity().getMaxAir();
    }

    @Override
    public void setMaxAir(int ticks) {
        getMinecraftEntity().maxAirOverride = OptionalInt.of(ticks);
    }

    @Override
    public void resetMaxAir() {
        getMinecraftEntity().maxAirOverride = OptionalInt.empty();
    }

    @Override
    public int getNoDamageTicks() {
        return getMinecraftEntity().hurtTime; // TODO check - was mapped wrongly in pre-loom, so not sure
    }

    @Override
    public void setNoDamageTicks(int ticks) { // TODO check - was mapped wrongly in pre-loom, so not sure
        getMinecraftEntity().hurtTime = ticks;
    }

    @Override
    public void swingHand(Hand hand) {
        getMinecraftEntity().swingHand(net.minecraft.util.Hand.valueOf(hand.name()));
    }

    @Override
    public boolean isUsingRiptide() {
        return getMinecraftEntity().isUsingRiptide();
    }

    @Override
    public boolean isSleeping() {
        return getMinecraftEntity().isSleeping();
    }

    @Override
    public boolean hasLineOfSight(@NonNull Entity entity) {
        return getMinecraftEntity().canSee(((EntityImpl) entity).getMinecraftEntity());
    }

    @Override
    public boolean canPickupItems() {
        return getMinecraftEntity().pickUpLoot;
    }

    @Override
    public void setCanPickItems(boolean flag) {
        getMinecraftEntity().pickUpLoot = flag;
    }

    @Override
    public Sound getHurtSound() {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public Sound getHurtSound(org.loomdev.api.entity.damage.@NonNull DamageSource damageSource) {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public void setHurtSound(@NonNull Sound sound) {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public void setHurtSound(org.loomdev.api.entity.damage.@NonNull DamageSource damageSource, @NonNull Sound sound) {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public void playHurtSound() {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public void playHurtSound(org.loomdev.api.entity.damage.@NonNull DamageSource damageSource) {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public Sound getDeathSound() {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public void setDeathSound(@NonNull Sound sound) {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public Sound getFallSound() {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public void setFallSound(@NonNull Sound sound) {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public Sound getDrinkSound() {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public void setDrinkSound(@NonNull Sound sound) {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public Sound getEatSound() {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public void setEatSound(@NonNull Sound sound) {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public float getSoundVolume() {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public void setSoundVolume(float v) {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public float getSoundPitch() {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public void setSoundPitch(float v) {
        throw new UnsupportedOperationException("This operation is currently not yet supported.");
    }

    @Override
    public boolean isAlive() {
        return getMinecraftEntity().isAlive();
    }

    @Override
    public int getStuckArrowCount() {
        return getMinecraftEntity().getStuckArrowCount();
    }

    @Override
    public void setStuckArrowCount(int count) {
        getMinecraftEntity().setStuckArrowCount(count);
    }

    @Override
    public int getStingerCount() {
        return getMinecraftEntity().getStingerCount();
    }

    @Override
    public void setStringerCount(int count) {
        getMinecraftEntity().setStingerCount(count);
    }

    @Override
    public boolean isHolding(@NonNull Material material) {
        return false; // TODO transform
    }

    @Override
    public boolean isHolding(@NonNull Predicate<Material> predicate) {
        return false; // TODO transform
    }

    @Override
    public @NonNull Optional<ItemStack> getStackInHand(@NonNull Hand hand) {
        return Optional.empty(); // TODO transform
    }

    @Override
    public void setStackInHand(@NonNull Hand hand, @NonNull ItemStack itemStack) {
        // TODO transform
    }

    @Override
    public float getMovementSpeed() {
        return getMinecraftEntity().getMovementSpeed();
    }

    @Override
    public void setMovementSpeed(float v) {
        getMinecraftEntity().setMovementSpeed(v);
    }

    @Override
    public boolean isHurtByWater() {
        return getMinecraftEntity().hurtByWater();
    }

    @Override
    public void setHurtByWater(boolean flag) {
        getMinecraftEntity().hurtByWaterOverride = Optional.of(flag);
    }

    @Override
    public void resetHurtByWater() {
        getMinecraftEntity().hurtByWaterOverride = Optional.empty();
    }

    @Override
    public boolean canSee(@NonNull Entity entity) {
        return getMinecraftEntity().canSee(((EntityImpl) entity).getMinecraftEntity());
    }

    @Override
    public float getHeadYaw() {
        return getMinecraftEntity().getHeadYaw();
    }

    @Override
    public void setHeadYaw(float v) {
        getMinecraftEntity().setHeadYaw(v);
    }

    @Override
    public float getBodyYaw() {
        return getMinecraftEntity().getYaw(1.0F);
    }

    @Override
    public void setBodyYaw(float v) {
        getMinecraftEntity().setYaw(v);
    }

    @Override
    public boolean isUsingItem() {
        return getMinecraftEntity().isUsingItem();
    }

    @Override
    public @NonNull Hand getActiveHand() {
        return Hand.valueOf(getMinecraftEntity().getActiveHand().name());
    }

    @Override
    public void setCurrentHand(Hand hand) {
        getMinecraftEntity().setCurrentHand(net.minecraft.util.Hand.valueOf(hand.name()));
    }

    @Override
    public boolean isGliding() {
        return getMinecraftEntity().isFallFlying();
    }

    @Override
    public Optional<Location> getSleepingPosition() {
        // TODO world??
        return getMinecraftEntity().getSleepingPosition().map(bp -> new Location(null, bp.getX(), bp.getY(), bp.getZ()));
    }

    @Override
    public void setSleepingPosition(Location location) {
        // TODO world??
        getMinecraftEntity().setSleepingPosition(new BlockPos(location.getX(), location.getY(), location.getZ()));
    }

    @Override
    public void clearSleepingPosition() {
        getMinecraftEntity().clearSleepingPosition();
    }

    @Override
    public ItemStack eatFood(World world, ItemStack itemStack) {
        return (ItemStack) null; // getMinecraftEntity().eatFood(null, null); // TODO transform
    }

    @Override
    public void damage(float damage) {
        this.damage(damage, null);
    }

    @Override
    public void damage(float damage, Entity source) {
        DamageSource reason = DamageSource.GENERIC;
        if (source instanceof PlayerImpl) {
            reason = DamageSource.player(((PlayerImpl) source).getMinecraftEntity());
        } else if (source instanceof LivingEntity) {
            reason = DamageSource.mob(((LivingEntityImpl) source).getMinecraftEntity());
        }
        getMinecraftEntity().damage(reason, damage);
    }

    @Override
    public float getHealth() {
        return getMinecraftEntity().getHealth();
    }

    @Override
    public void setHealth(float amount) {
        getMinecraftEntity().setHealth(amount);
    }

    @Override
    public float getMaxHealth() {
        return getMinecraftEntity().getMaxHealth();
    }

    @Override
    public void setMaxHealth(float amount) {
        Preconditions.checkArgument(amount > 0, "Max health must be greater than 0");
        getMinecraftEntity().getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(amount);

        if (getHealth() > amount) {
            setHealth(amount);
        }
    }

    @Override
    public void resetMaxHealth() {
        setMaxHealth((float) getMinecraftEntity().getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).getAttribute().getDefaultValue());
    }

    @Override
    public float getAbsorptionAmount() {
        return getMinecraftEntity().getAbsorptionAmount();
    }

    @Override
    public void setAbsorptionAmount(float amount) {
        Preconditions.checkArgument(amount >= 0 && Float.isFinite(amount), "amount < 0 or non-finite");
        getMinecraftEntity().setAbsorptionAmount(amount);
    }
}
