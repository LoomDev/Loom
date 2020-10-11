package org.loomdev.api.entity.effect;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.Entity;
import org.loomdev.api.entity.LivingEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AreaEffectCloudEntity extends Entity {

    @NotNull List<StatusEffect> getStatusEffects();

    @NotNull Optional<StatusEffect> getStatusEffect(@NotNull StatusEffect.Type type);

    void addStatusEffect(@NotNull StatusEffect effect);

    void removeStatusEffect(@NotNull StatusEffect.Type type);

    void clearStatusEffects();

    boolean hasStatusEffect(@NotNull StatusEffect.Type type);

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
