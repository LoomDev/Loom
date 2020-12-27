package org.loomdev.api.entity.effect;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.LivingEntity;
import org.loomdev.api.particle.Particle;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public interface AreaEffectCloud extends Entity {

    @NotNull
    Stream<StatusEffect> getStatusEffects();

    @NotNull
    Optional<StatusEffect> getStatusEffect(@NotNull StatusEffectType type);

    void addStatusEffect(@NotNull StatusEffect effect);

    void removeStatusEffect(@NotNull StatusEffectType type);

    boolean hasStatusEffect(@NotNull StatusEffectType type);

    void clearStatusEffects();

    int getDuration();

    void setDuration(int ticks);

    float getRadius();

    void setRadius(float radius);

    int getColor();

    void setColor(int color);

    int getWaitTime();

    void setWaitTime(int ticks);

    boolean isWaiting();

    void setWaiting(boolean waiting);

    float getRadiusOnUse();

    void setRadiusOnUse(float radius);

    float getRadiusPerTick();

    void setRadiusPerTick(float radius);

    @NotNull
    Optional<LivingEntity> getOwner();

    void setOwner(@Nullable LivingEntity entity);

    @NotNull
    Stream<LivingEntity> getAffectedEntities();

    @NotNull
    Particle getParticle();

    void setParticle(@NotNull Particle particle);
}
