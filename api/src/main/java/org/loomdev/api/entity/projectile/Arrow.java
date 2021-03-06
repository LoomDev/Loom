package org.loomdev.api.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.effect.StatusEffect;
import org.loomdev.api.entity.effect.StatusEffectType;
import org.loomdev.api.util.Color;

import java.util.List;
import java.util.Optional;

public interface Arrow extends AbstractArrow {

    @NotNull List<StatusEffect> getStatusEffects();

    @NotNull Optional<StatusEffect> getStatusEffect(@NotNull StatusEffectType type);

    void addStatusEffect(@NotNull StatusEffect effect);

    void removeStatusEffect(@NotNull StatusEffectType type);

    void clearStatusEffects();

    boolean hasStatusEffect(@NotNull StatusEffectType type);

    Color getColor();

    void setColor(Color color);

    // TODO potion?
}
