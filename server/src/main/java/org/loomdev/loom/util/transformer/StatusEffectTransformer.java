package org.loomdev.loom.util.transformer;

import net.minecraft.entity.effect.StatusEffectInstance;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.effect.StatusEffect;

public final class StatusEffectTransformer {

    private StatusEffectTransformer() {
        throw new UnsupportedOperationException("StatusEffectTransformer shouldn't be initialized.");
    }

    public static @NotNull StatusEffectInstance toMinecraft(@NotNull StatusEffect effect) {
        return new StatusEffectInstance(
                StatusEffectTypeTransformer.toMinecraft(effect.getType()),
                effect.getDuration(),
                effect.getAmplifier(),
                effect.isAmbient(),
                effect.isShowParticles(),
                effect.isShowIcon()
        );
    }

    public static StatusEffect toLoom(@NotNull StatusEffectInstance effect) {
        return StatusEffect.builder(StatusEffectTypeTransformer.toLoom(effect.getEffectType()))
                .duration(effect.getDuration())
                .amplifier(effect.getAmplifier())
                .ambient(effect.isAmbient())
                .showParticles(effect.shouldShowParticles())
                .showIcon(effect.shouldShowIcon())
                .build();
    }
}
