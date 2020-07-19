package org.loomdev.loom.util.transformer;

import net.minecraft.entity.effect.StatusEffectInstance;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.effect.StatusEffect;

public final class StatusEffectTransformer {

    private StatusEffectTransformer() {
        throw new UnsupportedOperationException("StatusEffectTransformer shouldn't be initialized.");
    }

    public static @NonNull StatusEffectInstance toMinecraft(@NonNull StatusEffect effect) {
        return new StatusEffectInstance(
                StatusEffectTypeTransformer.toMinecraft(effect.getType()),
                effect.getDuration(),
                effect.getAmplifier(),
                effect.isAmbient(),
                effect.isShowParticles(),
                effect.isShowIcon(),
                effect.getNextEffect().map(StatusEffectTransformer::toMinecraft).orElse(null)
        );
    }

    public static StatusEffect toLoom(StatusEffectInstance effect) {
        StatusEffect.Builder builder = new StatusEffect.Builder(StatusEffectTypeTransformer.toLoom(effect.getEffectType()))
                .duration(effect.getDuration())
                .amplifier(effect.getAmplifier())
                .ambient(effect.isAmbient())
                .showParticles(effect.shouldShowParticles())
                .showIcon(effect.shouldShowIcon());

        if (effect.hiddenEffect != null) {
            builder.nextEffect(toLoom(effect.hiddenEffect));
        }

        return builder.build();
    }
}
