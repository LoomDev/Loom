package org.loomdev.loom.util.transformer;

import net.minecraft.entity.effect.StatusEffectInstance;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.effect.StatusEffect;

public final class StatusEffectTransformer {
    private StatusEffectTransformer() { throw new UnsupportedOperationException("StatusEffectTransformer shouldn't be initialized."); }

    public static @NonNull StatusEffectInstance toMinecraft(@NonNull StatusEffect statusEffect) {
        return new StatusEffectInstance(
                StatusEffectTypeTransformer.toMinecraft(statusEffect.getType()),
                statusEffect.getDuration(),
                statusEffect.getAmplifier(),
                statusEffect.isAmbient(),
                statusEffect.isShowParticles(),
                statusEffect.isShowIcon(),
                statusEffect.getHiddenEffect() == null ? null : toMinecraft(statusEffect.getHiddenEffect())
        );
    }

    public static StatusEffect toLoom(StatusEffectInstance statusEffectInstance) {
        return new StatusEffect(
                StatusEffectTypeTransformer.toLoom(statusEffectInstance.getEffectType()),
                statusEffectInstance.getDuration(),
                statusEffectInstance.getAmplifier(),
                statusEffectInstance.isAmbient(),
                statusEffectInstance.shouldShowParticles(),
                statusEffectInstance.shouldShowIcon(),
                statusEffectInstance.hiddenEffect == null ? null : toLoom(statusEffectInstance.hiddenEffect)
        );
    }

}
