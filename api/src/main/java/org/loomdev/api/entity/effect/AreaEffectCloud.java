package org.loomdev.api.entity.effect;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.LivingEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AreaEffectCloud extends Entity {

    @NotNull List<StatusEffect> getStatusEffects();

    @Nullable
    StatusEffect getStatusEffect(@NotNull StatusEffectType type);

    void addStatusEffect(@NotNull StatusEffect effect);

    void removeStatusEffect(@NotNull StatusEffectType type);

    void clearStatusEffects();

    boolean hasStatusEffect(@NotNull StatusEffectType type);

    int getDuration();

    void setDuration(int ticks);

    float getRadius();

    void setRadius(float radius);

    int getColor();

    void setColor(int color);

    int getWaitTime();

    void setWaitTime(int ticks);

    boolean isWaiting();

    void  setWaiting(boolean flag);

    float getRadiusOnUse();

    void setRadiusOnUse(float radius);

    float getRadiusPerTick();

    void setRadiusPerTick(float radius);

    @NotNull Optional<LivingEntity> getOwner();

    void setOwner(@NotNull LivingEntity livingEntity);

    @NotNull Map<Entity, Integer> getAffectedEntities(); // TODO is this useful?

    // get/set particle effect

}
