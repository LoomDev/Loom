package org.loomdev.loom.entity;

import com.google.common.base.Preconditions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.effect.StatusEffect;
import org.loomdev.api.entity.effect.StatusEffectType;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemType;
import org.loomdev.api.util.EquipmentSlot;
import org.loomdev.api.util.Hand;
import org.loomdev.api.world.Location;
import org.loomdev.api.world.World;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.item.ItemStackImpl;
import org.loomdev.loom.util.transformer.StatusEffectTransformer;
import org.loomdev.loom.util.transformer.StatusEffectTypeTransformer;
import org.loomdev.loom.world.WorldImpl;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class LivingEntityImpl extends EntityImpl implements LivingEntity {

    public LivingEntityImpl(net.minecraft.world.entity.LivingEntity entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.LivingEntity getMinecraftEntity() {
        return (net.minecraft.world.entity.LivingEntity) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public List<StatusEffect> getStatusEffects() {
        return getMinecraftEntity().getActiveEffects().stream()
                .map(StatusEffectTransformer::toLoom)
                .collect(Collectors.toList());
    }

    @Override
    public void addStatusEffect(@NotNull StatusEffect statusEffect) {
        getMinecraftEntity().addEffect(StatusEffectTransformer.toMinecraft(statusEffect));
    }

    @Override
    @NotNull
    public Optional<StatusEffect> getStatusEffect(@NotNull StatusEffectType type) {
        return Optional.ofNullable(getMinecraftEntity().getEffect(StatusEffectTypeTransformer.toMinecraft(type)))
                .map(StatusEffectTransformer::toLoom);
    }

    @Override
    public void removeStatusEffect(@NotNull StatusEffectType statusEffectType) {
        getMinecraftEntity().removeEffect(StatusEffectTypeTransformer.toMinecraft(statusEffectType));
    }

    @Override
    public void clearStatusEffects() {
        getMinecraftEntity().removeAllEffects();
    }

    @Override
    public boolean hasStatusEffect(@NotNull StatusEffectType statusEffectType) {
        return getMinecraftEntity().hasEffect(StatusEffectTypeTransformer.toMinecraft(statusEffectType));
    }

    @Override
    public int getAirSupply() {
        return getMinecraftEntity().getAirSupply();
    }

    @Override
    public void setAirSupply(int ticks) {
        getMinecraftEntity().setAirSupply(ticks);
    }

    @Override
    public int getMaxAirSupply() {
        return getMinecraftEntity().getMaxAirSupply();
    }

    @Override
    public void setMaxAirSupply(int ticks) {
        getMinecraftEntity().maxAirSupplyOverride = OptionalInt.of(ticks);
    }

    @Override
    public void resetMaxAir() {
        getMinecraftEntity().maxAirSupplyOverride = OptionalInt.empty();
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
    public void swingHand(@NotNull Hand hand) {
        getMinecraftEntity().swing(InteractionHand.valueOf(hand.name()));
    }

    @Override
    public int getRiptideTicks() {
        return getMinecraftEntity().autoSpinAttackTicks;
    }

    @Override
    public void setRiptideTicks(int ticks) {
        getMinecraftEntity().startAutoSpinAttack(ticks);
    }

    @Override
    public boolean isUsingRiptide() {
        return getMinecraftEntity().isAutoSpinAttack();
    }

    @Override
    public boolean isSleeping() {
        return getMinecraftEntity().isSleeping();
    }

    @Override
    public boolean hasLineOfSight(@NotNull Entity entity) {
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
    public int getStuckArrowCount() {
        return getMinecraftEntity().getArrowCount();
    }

    @Override
    public void setStuckArrowCount(int count) {
        getMinecraftEntity().setArrowCount(count);
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
    public boolean isHolding(@NotNull ItemType type) {
        return isHolding(type1 -> type1.equals(type));
    }

    @Override
    public boolean isHolding(@NotNull Predicate<ItemType> predicate) {
        var mainHand = getEquipment(EquipmentSlot.MAIN_HAND);
        var offHand = getEquipment(EquipmentSlot.OFF_HAND);

        if (mainHand.isPresent() && predicate.test(mainHand.get().getType())) {
            return true;
        }

        return offHand.isPresent() && predicate.test(offHand.get().getType());
    }

    @Override
    @NotNull
    public Optional<ItemStack> getEquipment(@NotNull EquipmentSlot slot) {
        return Optional.ofNullable(getMinecraftEntity().getItemBySlot(getMinecraftEquipmentSlot(slot)))
                .map(ItemStackImpl::of);
    }

    @Override
    public void setEquipment(@NotNull EquipmentSlot slot, @NotNull ItemStack equipment) {
        getMinecraftEntity().setItemSlot(getMinecraftEquipmentSlot(slot), ((ItemStackImpl) equipment).getMinecraftItemStack());
    }

    private net.minecraft.world.entity.EquipmentSlot getMinecraftEquipmentSlot(@NotNull EquipmentSlot slot) {
        return net.minecraft.world.entity.EquipmentSlot.values()[slot.ordinal()];
    }

    @Override
    public float getMovementSpeed() {
        return getMinecraftEntity().getSpeed();
    }

    @Override
    public void setMovementSpeed(float v) {
        getMinecraftEntity().setSpeed(v);
    }

    @Override
    public boolean isSensitiveToWater() {
        return getMinecraftEntity().sensitiveToWaterOverride.orElse(false);
    }

    @Override
    public void setSensitiveToWater(boolean flag) {
        getMinecraftEntity().sensitiveToWaterOverride = Optional.of(flag);
    }

    @Override
    public void resetSensitiveToWater() {
        getMinecraftEntity().sensitiveToWaterOverride = Optional.empty();
    }

    @Override
    public boolean canSee(@NotNull Entity entity) {
        return getMinecraftEntity().canSee(((EntityImpl) entity).getMinecraftEntity());
    }

    @Override
    public float getHeadYaw() {
        return getMinecraftEntity().getYHeadRot();
    }

    @Override
    public void setHeadYaw(float v) {
        getMinecraftEntity().setYHeadRot(v);
    }

    @Override
    public float getBodyYaw() {
        return getMinecraftEntity().getViewYRot(1.0F);
    }

    @Override
    public void setBodyYaw(float v) {
        getMinecraftEntity().setYBodyRot(v);
    }

    @Override
    public boolean isUsingItem() {
        return getMinecraftEntity().isUsingItem();
    }

    @Override
    @NotNull
    public Hand getActiveHand() {
        return Hand.valueOf(getMinecraftEntity().getUsedItemHand().name());
    }

    @Override
    public void setActiveHand(@NotNull Hand hand) {
        getMinecraftEntity().setLivingEntityFlag(hand.getEquipmentSlot().getEntitySlotId(), false);
    }

    @Override
    public boolean isGliding() {
        return getMinecraftEntity().isFallFlying();
    }

    @Override
    public Optional<Location> getSleepingPosition() {
        // TODO world??
        return getMinecraftEntity().getSleepingPos().map(bp -> new Location(null, bp.getX(), bp.getY(), bp.getZ()));
    }

    @Override
    public void setSleepingPosition(Location location) {
        // TODO world??
        getMinecraftEntity().setSleepingPos(new BlockPos(location.getX(), location.getY(), location.getZ()));
    }

    @Override
    public void clearSleepingPosition() {
        getMinecraftEntity().clearSleepingPos();
    }

    @Override
    public void wakeUp() {
        getMinecraftEntity().stopSleeping();
    }

    @Override
    public ItemStack eatFood(World world, ItemStack itemStack) {
        var mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();
        return ItemStackImpl.of(getMinecraftEntity().eat(((WorldImpl) world).getMinecraftWorld(), mcStack));
    }

    @Override
    public void damage(float damage) {
        this.damage(damage, null);
    }

    @Override
    public void damage(float damage, Entity source) {
        var reason = DamageSource.GENERIC;
        if (source instanceof PlayerImpl) {
            reason = DamageSource.playerAttack(((PlayerImpl) source).getMinecraftEntity());
        } else if (source instanceof LivingEntity) {
            reason = DamageSource.mobAttack(((LivingEntityImpl) source).getMinecraftEntity());
        }
        getMinecraftEntity().hurt(reason, damage);
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
        var attribute = getMinecraftEntity().getAttribute(Attributes.MAX_HEALTH);
        if (attribute == null) return;
        attribute.setBaseValue(amount);

        if (getHealth() > amount) {
            setHealth(amount);
        }
    }

    @Override
    public void resetMaxHealth() {
        var attribute = getMinecraftEntity().getAttribute(Attributes.MAX_HEALTH);
        if (attribute == null) return;
        setMaxHealth((float) attribute.getAttribute().getDefaultValue());
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
