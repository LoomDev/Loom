package org.loomdev.loom.entity;

import com.google.common.base.Preconditions;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.effect.StatusEffect;
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
    public @NotNull List<StatusEffect> getStatusEffects() {
        return getMinecraftEntity().getStatusEffects().stream()
                .map(StatusEffectTransformer::toLoom)
                .collect(Collectors.toList());
    }

    @Override
    public void addStatusEffect(@NotNull StatusEffect statusEffect) {
        getMinecraftEntity().addStatusEffect(StatusEffectTransformer.toMinecraft(statusEffect));
    }

    @Override
    public @NotNull Optional<StatusEffect> getStatusEffect(@NotNull StatusEffect.Type statusEffectType) {
        StatusEffectInstance statusEffectInstance = getMinecraftEntity().getStatusEffect(StatusEffectTypeTransformer.toMinecraft(statusEffectType));
        return Optional.ofNullable(statusEffectInstance).map(StatusEffectTransformer::toLoom);
    }

    @Override
    public void removeStatusEffect(@NotNull StatusEffect.Type statusEffectType) {
        getMinecraftEntity().removeStatusEffect(StatusEffectTypeTransformer.toMinecraft(statusEffectType));
    }

    @Override
    public void clearStatusEffects() {
        getMinecraftEntity().clearStatusEffects();
    }

    @Override
    public boolean hasStatusEffect(@NotNull StatusEffect.Type statusEffectType) {
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
        getMinecraftEntity().maxAirOverride = Optional.of(ticks);
    }

    @Override
    public void resetMaxAir() {
        getMinecraftEntity().maxAirOverride = Optional.empty();
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
    public boolean isHolding(@NotNull ItemType item) {
        return isHolding(mat -> mat.equals(item));
    }

    @Override
    public boolean isHolding(@NotNull Predicate<ItemType> predicate) {
        Optional<ItemStack> mainHand = getItemInHand(Hand.MAIN_HAND);
        Optional<ItemStack> offHand = getItemInHand(Hand.OFF_HAND);
        if (mainHand.isPresent() && predicate.test(mainHand.get().getType())) {
            return true;
        }

        return offHand.isPresent() && predicate.test(offHand.get().getType());
    }

    @Override
    public @NotNull Optional<ItemStack> getItemInHand(@NotNull Hand hand) {
        net.minecraft.item.ItemStack mcStack = getMinecraftEntity().getEquippedStack(hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        if(mcStack == net.minecraft.item.ItemStack.EMPTY)
            return Optional.empty();
        return Optional.of(new ItemStackImpl(mcStack));
    }

    @Override
    public void setItemInHand(@NotNull Hand hand, @NotNull ItemStack itemStack) {
        getMinecraftEntity().equipStack(hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND, ((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    private Optional<ItemStack> getStack(EquipmentSlot mcEquipmentSlot) {
        net.minecraft.item.ItemStack mcStack = getMinecraftEntity().getEquippedStack(mcEquipmentSlot);
        if(mcStack == net.minecraft.item.ItemStack.EMPTY)
            return Optional.empty();
        return Optional.of(new ItemStackImpl(mcStack));
    }

    @Override
    public @NotNull Optional<ItemStack> getBoots() {
        return getStack(EquipmentSlot.FEET);
    }

    @Override
    public void setBoots(@NotNull ItemStack itemStack) {
        getMinecraftEntity().equipStack(EquipmentSlot.FEET, ((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    @Override
    public @NotNull Optional<ItemStack> getLeggings() {
        return getStack(EquipmentSlot.LEGS);
    }

    @Override
    public void setLeggings(@NotNull ItemStack itemStack) {
        getMinecraftEntity().equipStack(EquipmentSlot.LEGS, ((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    @Override
    public @NotNull Optional<ItemStack> getChestplate() {
        return getStack(EquipmentSlot.CHEST);
    }

    @Override
    public void setChestplate(@NotNull ItemStack itemStack) {
        getMinecraftEntity().equipStack(EquipmentSlot.CHEST, ((ItemStackImpl) itemStack).getMinecraftItemStack());
    }

    @Override
    public @NotNull Optional<ItemStack> getHelmet() {
        return getStack(EquipmentSlot.HEAD);
    }

    @Override
    public void setHelmet(@NotNull ItemStack itemStack) {
        getMinecraftEntity().equipStack(EquipmentSlot.HEAD, ((ItemStackImpl) itemStack).getMinecraftItemStack());
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
    public boolean canSee(@NotNull Entity entity) {
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
    public @NotNull Hand getActiveHand() {
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
        return new ItemStackImpl(getMinecraftEntity().eatFood(((WorldImpl) world).getMinecraftWorld(),
                ((ItemStackImpl)itemStack).getMinecraftItemStack()));
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
