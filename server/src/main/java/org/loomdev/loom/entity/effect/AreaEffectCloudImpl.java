package org.loomdev.loom.entity.effect;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.effect.AreaEffectCloud;
import org.loomdev.api.entity.effect.StatusEffect;
import org.loomdev.api.entity.effect.StatusEffectType;
import org.loomdev.loom.entity.EntityImpl;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.util.transformer.StatusEffectTransformer;
import org.loomdev.loom.util.transformer.StatusEffectTypeTransformer;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AreaEffectCloudImpl extends EntityImpl implements AreaEffectCloud {

    public AreaEffectCloudImpl(net.minecraft.world.entity.AreaEffectCloud entity) {
        super(entity);
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.AreaEffectCloud getMinecraftEntity() {
        return (net.minecraft.world.entity.AreaEffectCloud) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public EntityType<AreaEffectCloud> getType() {
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
    @Nullable
    public StatusEffect getStatusEffect(@NotNull StatusEffectType statusEffectType) {
        for (var effect : getMinecraftEntity().effects) {
            if (effect.getEffect().equals(StatusEffectTypeTransformer.toMinecraft(statusEffectType))) {
                return StatusEffectTransformer.toLoom(effect);
            }
        }
        return null;
    }

    @Override
    public void removeStatusEffect(@NotNull StatusEffectType statusEffectType) {
        var effectsToRemove = getMinecraftEntity().effects.stream()
                .filter(effect -> effect.getEffect().equals(StatusEffectTypeTransformer.toMinecraft(statusEffectType)))
                .collect(Collectors.toList());

        getMinecraftEntity().effects.removeAll(effectsToRemove);
    }

    @Override
    public void clearStatusEffects() {
        getMinecraftEntity().effects.clear();
    }

    @Override
    public boolean hasStatusEffect(@NotNull StatusEffectType statusEffectType) {
        return getStatusEffect(statusEffectType) != null;
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
        getMinecraftEntity().setFixedColor(color);
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
        return getMinecraftEntity().radiusPerTick;
    }

    @Override
    public void setRadiusPerTick(float radius) {
        getMinecraftEntity().radiusPerTick = radius;
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
    @NotNull
    public Map<Entity, Integer> getAffectedEntities() {
        return getMinecraftEntity().victims.entrySet().stream()
                .collect(Collectors.toMap(es -> es.getKey().getLoomEntity(), Map.Entry::getValue));
    }
}
