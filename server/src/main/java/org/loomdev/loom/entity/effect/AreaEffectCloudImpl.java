package org.loomdev.loom.entity.effect;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.entity.effect.AreaEffectCloud;
import org.loomdev.api.entity.effect.StatusEffect;
import org.loomdev.api.entity.effect.StatusEffectType;
import org.loomdev.api.particle.Particle;
import org.loomdev.loom.entity.EntityImpl;
import org.loomdev.loom.entity.LivingEntityImpl;
import org.loomdev.loom.util.transformer.ParticleTransformer;
import org.loomdev.loom.util.transformer.StatusEffectTransformer;
import org.loomdev.loom.util.transformer.StatusEffectTypeTransformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
    @NotNull
    public List<StatusEffect> getStatusEffects() {
        var effects = getMinecraftEntity().effects;
        if (effects.size() == 0) {
            return Collections.emptyList();
        }

        var loomEffects = new ArrayList<StatusEffect>();
        for (var effect : effects) {
            loomEffects.add(StatusEffectTransformer.toLoom(effect));
        }

        return loomEffects;
    }

    @Override
    @Nullable
    public StatusEffect getStatusEffect(@NotNull StatusEffectType type) {
        for (var effect : getMinecraftEntity().effects) {
            if (effect.getEffect().equals(StatusEffectTypeTransformer.toMinecraft(type))) {
                return StatusEffectTransformer.toLoom(effect);
            }
        }

        return null;
    }

    @Override
    public void addStatusEffect(@NotNull StatusEffect effect) {
        getMinecraftEntity().addEffect(StatusEffectTransformer.toMinecraft(effect));
    }

    @Override
    public void removeStatusEffect(@NotNull StatusEffectType type) {
        getMinecraftEntity().effects.removeIf(effect -> effect.getEffect().equals(StatusEffectTypeTransformer.toMinecraft(type)));
    }

    @Override
    public boolean hasStatusEffect(@NotNull StatusEffectType statusEffectType) {
        return getStatusEffect(statusEffectType) != null;
    }

    @Override
    public void clearStatusEffects() {
        getMinecraftEntity().effects.clear();
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
    public void setWaiting(boolean waiting) {
        getMinecraftEntity().setWaiting(waiting);
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
    @Nullable
    public LivingEntity getOwner() {
        var owner = getMinecraftEntity().getOwner();
        if (owner == null) {
            return null;
        }

        return (LivingEntity) owner.getLoomEntity();
    }

    @Override
    public void setOwner(@Nullable LivingEntity entity) {
        if (entity == null) {
            getMinecraftEntity().setOwner(null);
        } else {
            getMinecraftEntity().setOwner(((LivingEntityImpl) entity).getMinecraftEntity());
        }
    }

    @Override
    @NotNull
    public Map<Entity, Integer> getAffectedEntities() {
        return getMinecraftEntity().victims.entrySet().stream()
                .collect(Collectors.toMap(es -> es.getKey().getLoomEntity(), Map.Entry::getValue));
    }

    @Override
    @NotNull
    public Particle getParticle() {
        return null;
        // return ParticleTransformer.toLoom(); TODO update when we have a toLoom transformer
    }

    @Override
    public void setParticle(@NotNull Particle particle) {
        getMinecraftEntity().setParticle(ParticleTransformer.toMinecraft(particle));
    }
}
