package org.loomdev.loom.entity.projectile;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.effect.StatusEffect;
import org.loomdev.api.entity.effect.StatusEffectType;
import org.loomdev.api.entity.projectile.Arrow;
import org.loomdev.api.util.Color;
import org.loomdev.loom.util.transformer.StatusEffectTransformer;
import org.loomdev.loom.util.transformer.StatusEffectTypeTransformer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArrowImpl extends PersistentProjectileImpl implements Arrow {

    public ArrowImpl(ArrowEntity entity) {
        super(entity);
    }

    @Override
    public ArrowEntity getMinecraftEntity() {
        return (ArrowEntity) super.getMinecraftEntity();
    }

    @Override
    public @NonNull List<StatusEffect> getStatusEffects() {
        return getMinecraftEntity().effects.stream()
                .map(StatusEffectTransformer::toLoom)
                .collect(Collectors.toList());
    }

    @Override
    public @NonNull Optional<StatusEffect> getStatusEffect(@NonNull StatusEffectType statusEffectType) {
        return getMinecraftEntity().effects.stream()
                .filter(effect -> effect.getEffectType().equals(StatusEffectTypeTransformer.toMinecraft(statusEffectType)))
                .findFirst()
                .map(StatusEffectTransformer::toLoom);
    }

    @Override
    public void addStatusEffect(@NonNull StatusEffect statusEffect) {
        getMinecraftEntity().effects.add(StatusEffectTransformer.toMinecraft(statusEffect));
    }

    @Override
    public void removeStatusEffect(@NonNull StatusEffectType statusEffectType) {
        getMinecraftEntity().effects.stream()
                .filter(effect -> effect.getEffectType().equals(StatusEffectTypeTransformer.toMinecraft(statusEffectType)))
                .findFirst()
                .ifPresent(effect -> getMinecraftEntity().effects.remove(effect));
    }

    @Override
    public void clearStatusEffects() {
        getMinecraftEntity().effects.clear();
    }

    @Override
    public boolean hasStatusEffect(@NonNull StatusEffectType statusEffectType) {
        return getMinecraftEntity().effects.stream()
                .anyMatch(effect -> effect.getEffectType().equals(StatusEffectTypeTransformer.toMinecraft(statusEffectType)));
    }

    @Override
    public Color getColor() {
        return Color.fromRgb(getMinecraftEntity().getColor());
    }

    @Override
    public void setColor(Color color) {
        getMinecraftEntity().setColor(color.asRgb());
    }
}
