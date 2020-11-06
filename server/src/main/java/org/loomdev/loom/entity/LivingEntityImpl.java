package org.loomdev.loom.entity;

import com.google.common.base.Preconditions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.effect.StatusEffect;
import org.loomdev.api.entity.effect.StatusEffectType;
import org.loomdev.api.item.ItemStack;
import org.loomdev.api.item.ItemType;
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
    public @NotNull List<StatusEffect> getStatusEffects() {
        return getMinecraftEntity().getActiveEffects().stream()
                .map(StatusEffectTransformer::toLoom)
                .collect(Collectors.toList());
    }

    @Override
    public void addStatusEffect(@NotNull StatusEffect statusEffect) {
        getMinecraftEntity().addEffect(StatusEffectTransformer.toMinecraft(statusEffect));
    }

    @Override
    @Nullable
    public StatusEffect getStatusEffect(@NotNull StatusEffectType type) {
        var effect = getMinecraftEntity().getEffect(StatusEffectTypeTransformer.toMinecraft(type));
        if (effect == null) return null;
        return StatusEffectTransformer.toLoom(effect);
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
    public boolean isUsingRiptide() {
        return false; // TODO ??
        // return getMinecraftEntity().isUsingRiptide();
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
    public boolean isAlive() {
        return getMinecraftEntity().isAlive();
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
    public boolean isHolding(@NotNull ItemType item) {
        return isHolding(mat -> mat.equals(item));
    }

    @Override
    public boolean isHolding(@NotNull Predicate<ItemType> predicate) {
        var mainHand = getItemInHand(Hand.MAIN_HAND);
        var offHand = getItemInHand(Hand.OFF_HAND);

        if (mainHand != null && predicate.test(mainHand.getType())) {
            return true;
        }

        return offHand != null && predicate.test(offHand.getType());
    }

    @Override
    @Nullable
    public ItemStack getItemInHand(@NotNull Hand hand) {
        var mcStack = getMinecraftEntity().getItemInHand(hand == Hand.MAIN_HAND ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND);
        if (mcStack == net.minecraft.world.item.ItemStack.EMPTY) {
            return null;
        }

        return ItemStackImpl.of(mcStack);
    }

    @Override
    public void setItemInHand(@NotNull Hand hand, @NotNull ItemStack itemStack) {
        var mcStack = ((ItemStackImpl) itemStack).getMinecraftItemStack();
        getMinecraftEntity().setItemInHand(hand == Hand.MAIN_HAND ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND, mcStack);
    }

    @Nullable
    private ItemStack getStack(@NotNull EquipmentSlot mcEquipmentSlot) {
        var mcStack = getMinecraftEntity().getItemBySlot(mcEquipmentSlot);
        if (mcStack == null || mcStack.isEmpty()) {
            return null;
        }

        return ItemStackImpl.of(mcStack);
    }

    @Override
    @Nullable
    public ItemStack getBoots() {
        return getStack(EquipmentSlot.FEET);
    }

    @Override
    public void setBoots(@NotNull ItemStack itemStack) {
        getMinecraftEntity().setItemSlot(EquipmentSlot.FEET, ((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    @Override
    @Nullable
    public ItemStack getLeggings() {
        return getStack(EquipmentSlot.LEGS);
    }

    @Override
    public void setLeggings(@NotNull ItemStack itemStack) {
        getMinecraftEntity().setItemSlot(EquipmentSlot.LEGS, ((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    @Override
    @Nullable
    public ItemStack getChestplate() {
        return getStack(EquipmentSlot.CHEST);
    }

    @Override
    public void setChestplate(@NotNull ItemStack itemStack) {
        getMinecraftEntity().setItemSlot(EquipmentSlot.CHEST, ((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    @Override
    @Nullable
    public ItemStack getHelmet() {
        return getStack(EquipmentSlot.HEAD);
    }

    @Override
    public void setHelmet(@NotNull ItemStack itemStack) {
        getMinecraftEntity().setItemSlot(EquipmentSlot.HEAD, ((ItemStackImpl) itemStack).getMinecraftItemStack());
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
