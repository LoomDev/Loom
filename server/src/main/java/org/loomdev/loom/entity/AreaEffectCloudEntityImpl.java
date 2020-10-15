package org.loomdev.loom.entity;

import net.minecraft.entity.effect.StatusEffectInstance;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.effect.AreaEffectCloudEntity;
import org.loomdev.api.entity.effect.StatusEffect;
import org.loomdev.api.entity.effect.StatusEffectType;
import org.loomdev.loom.util.transformer.StatusEffectTransformer;
import org.loomdev.loom.util.transformer.StatusEffectTypeTransformer;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AreaEffectCloudEntityImpl extends EntityImpl implements AreaEffectCloudEntity {

    public AreaEffectCloudEntityImpl(net.minecraft.entity.AreaEffectCloudEntity entity) {
        super(entity);
    }

    @Override
    public net.minecraft.entity.@NotNull AreaEffectCloudEntity getMinecraftEntity() {
        return (net.minecraft.entity.AreaEffectCloudEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.AREA_EFFECT_CLOUD;
    }

    @Override
    public @NotNull List<StatusEffect> getStatusEffects() {
        return getMinecraftEntity().effects.stream().map(StatusEffectTransformer::toLoom).collect(Collectors.toList());
    }

    @Override
    public void addStatusEffect(@NotNull StatusEffect statusEffect) {
        getMinecraftEntity().addEffect(StatusEffectTransformer.toMinecraft(statusEffect));
    }

    @Override
    public @NotNull Optional<StatusEffect> getStatusEffect(@NotNull StatusEffectType statusEffectType) {
        for (net.minecraft.entity.effect.StatusEffectInstance effect : getMinecraftEntity().effects) {
            if (effect.getEffectType().equals(StatusEffectTypeTransformer.toMinecraft(statusEffectType))) {
                return Optional.of(StatusEffectTransformer.toLoom(effect));
            }
        }
        return Optional.empty();
    }

    @Override
    public void removeStatusEffect(@NotNull StatusEffectType statusEffectType) {
        List<StatusEffectInstance> effectsToRemove = getMinecraftEntity().effects.stream()
                .filter(effect -> effect.getEffectType().equals(StatusEffectTypeTransformer.toMinecraft(statusEffectType)))
                .collect(Collectors.toList());

        getMinecraftEntity().effects.removeAll(effectsToRemove);
    }

    @Override
    public void clearStatusEffects() {
        getMinecraftEntity().effects.clear();
    }

    @Override
    public boolean hasStatusEffect(@NotNull StatusEffectType statusEffectType) {
        return getStatusEffect(statusEffectType).isPresent();
    }

    @Override
    public int getDuration() {
        return getMinecraftEntity().getDuration();
    }

    @Override
    public void setDuration(int duration) {
        getMinecraftEntity().setDuration(duration);
    }

    @Override
    public float getRadius() {
        return getMinecraftEntity().getRadius();
    }

    @Override
    public void setRadius(float radius) {
        getMinecraftEntity().setRadius(radius);
    }

    @Override
    public int getColor() {
        return getMinecraftEntity().getColor();
    }

    @Override
    public void setColor(int color) {
        getMinecraftEntity().setColor(color);
    }

    @Override
    public int getWaitTime() {
        return getMinecraftEntity().waitTime;
    }

    @Override
    public void setWaitTime(int ticks) {
        getMinecraftEntity().setWaitTime(ticks);
    }

    @Override
    public boolean isWaiting() {
        return getMinecraftEntity().isWaiting();
    }

    @Override
    public void setWaiting(boolean flag) {
        getMinecraftEntity().setWaiting(flag);
    }

    @Override
    public float getRadiusOnUse() {
        return getMinecraftEntity().radiusOnUse;
    }

    @Override
    public void setRadiusOnUse(float radius) {
        getMinecraftEntity().radiusOnUse = radius;
    }

    @Override
    public float getRadiusPerTick() {
        return getMinecraftEntity().radiusGrowth;
    }

    @Override
    public void setRadiusPerTick(float radius) {
        getMinecraftEntity().radiusGrowth = radius;
    }

    @Override
    public @NotNull Optional<LivingEntity> getOwner() {
        return Optional.ofNullable(getMinecraftEntity().getOwner()).map(e -> (LivingEntityImpl) e.getLoomEntity());
    }

    @Override
    public void setOwner(@NotNull LivingEntity livingEntity) {
        getMinecraftEntity().setOwner(((LivingEntityImpl) livingEntity).getMinecraftEntity());
    }

    @Override
    public @NotNull Map<Entity, Integer> getAffectedEntities() {
        return getMinecraftEntity().affectedEntities.entrySet().stream()
                .collect(Collectors.toMap(es -> es.getKey().getLoomEntity(), Map.Entry::getValue));
    }
}
