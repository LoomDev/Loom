package org.loomdev.loom.entity;

import com.google.common.base.Preconditions;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.effect.StatusEffect;
import org.loomdev.api.entity.effect.StatusEffectType;
import org.loomdev.api.util.Hand;
import org.loomdev.loom.entity.player.PlayerImpl;
import org.loomdev.loom.util.transformer.StatusEffectTransformer;
import org.loomdev.loom.util.transformer.StatusEffectTypeTransformer;

import java.util.List;
import java.util.Optional;
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
        return getMinecraftEntity().maxAir;
    }

    @Override
    public void setMaxAir(int ticks) {
        getMinecraftEntity().maxAir = ticks;
    }

    @Override
    public void resetMaxAir() {
        getMinecraftEntity().resetMaxAir();
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
