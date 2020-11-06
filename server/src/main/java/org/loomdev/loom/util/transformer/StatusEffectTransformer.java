package org.loomdev.loom.util.transformer;

import net.minecraft.world.effect.MobEffectInstance;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.effect.StatusEffect;

public final class StatusEffectTransformer {

    private StatusEffectTransformer() {
        throw new UnsupportedOperationException("StatusEffectTransformer shouldn't be initialized.");
    }

    @NotNull
    public static MobEffectInstance toMinecraft(@NotNull StatusEffect effect) {
        return new MobEffectInstance(
                StatusEffectTypeTransformer.toMinecraft(effect.getType()),
                effect.getDuration(),
                effect.getAmplifier(),
                effect.isAmbient(),
                effect.isShowParticles(),
                effect.isShowIcon()
        );
    }

    @NotNull
    public static StatusEffect toLoom(@NotNull MobEffectInstance effect) {
        return StatusEffect.builder(StatusEffectTypeTransformer.toLoom(effect.getEffect()))
                .duration(effect.getDuration())
                .amplifier(effect.getAmplifier())
                .ambient(effect.isAmbient())
                .showParticles(effect.isVisible())
                .showIcon(effect.showIcon())
                .build();
    }
}
