package org.loomdev.loom.entity.projectile;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.effect.StatusEffect;
import org.loomdev.api.entity.effect.StatusEffectType;
import org.loomdev.api.entity.projectile.Arrow;
import org.loomdev.api.util.Color;
import org.loomdev.loom.util.transformer.StatusEffectTransformer;
import org.loomdev.loom.util.transformer.StatusEffectTypeTransformer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArrowImpl extends AbstractArrowImpl implements Arrow {

    public ArrowImpl(net.minecraft.world.entity.projectile.Arrow entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Arrow> getType() {
        return EntityType.ARROW;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.projectile.Arrow getMinecraftEntity() {
        return (net.minecraft.world.entity.projectile.Arrow) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public List<StatusEffect> getStatusEffects() {
        return getMinecraftEntity().effects.stream()
                .map(StatusEffectTransformer::toLoom)
                .collect(Collectors.toList());
    }

    @Override
    @NotNull
    public Optional<StatusEffect> getStatusEffect(@NotNull StatusEffectType statusEffectType) {
        return getMinecraftEntity().effects.stream()
                .filter(effect -> effect.getEffect().equals(StatusEffectTypeTransformer.toMinecraft(statusEffectType)))
                .findFirst()
                .map(StatusEffectTransformer::toLoom);
    }

    @Override
    public void addStatusEffect(@NotNull StatusEffect statusEffect) {
        getMinecraftEntity().effects.add(StatusEffectTransformer.toMinecraft(statusEffect));
    }

    @Override
    public void removeStatusEffect(@NotNull StatusEffectType statusEffectType) {
        getMinecraftEntity().effects.stream()
                .filter(effect -> effect.getEffect().equals(StatusEffectTypeTransformer.toMinecraft(statusEffectType)))
                .findFirst()
                .ifPresent(effect -> getMinecraftEntity().effects.remove(effect));
    }

    @Override
    public void clearStatusEffects() {
        getMinecraftEntity().effects.clear();
    }

    @Override
    public boolean hasStatusEffect(@NotNull StatusEffectType statusEffectType) {
        return getMinecraftEntity().effects.stream()
                .anyMatch(effect -> effect.getEffect().equals(StatusEffectTypeTransformer.toMinecraft(statusEffectType)));
    }

    @Override
    public Color getColor() {
        return Color.fromRgb(getMinecraftEntity().getColor());
    }

    @Override
    public void setColor(Color color) {
        getMinecraftEntity().setFixedColor(color.asRgb());
    }
}
