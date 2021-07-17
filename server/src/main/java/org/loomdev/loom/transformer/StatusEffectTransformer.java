package org.loomdev.loom.transformer;

import net.minecraft.world.effect.MobEffectInstance;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.effect.StatusEffect;

public final class StatusEffectTransformer implements Transformer<MobEffectInstance, StatusEffect> {

    protected StatusEffectTransformer() {
    }

    @Override
    @NotNull
    public MobEffectInstance toMinecraft(@NotNull StatusEffect effect) {
        var type = Transformer.STATUS_EFFECT_TYPE.toMinecraft(effect.getType());
        return new MobEffectInstance(type, effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.isShowParticles(), effect.isShowIcon());
    }

    @Override
    @NotNull
    public StatusEffect toLoom(@NotNull MobEffectInstance effect) {
        var type = Transformer.STATUS_EFFECT_TYPE.toLoom(effect.getEffect());
        return StatusEffect.builder(type)
                .duration(effect.getDuration())
                .amplifier(effect.getAmplifier())
                .ambient(effect.isAmbient())
                .showParticles(effect.isVisible())
                .showIcon(effect.showIcon())
                .build();
    }
}
