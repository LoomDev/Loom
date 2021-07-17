package org.loomdev.loom.transformer;

import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffect;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.effect.StatusEffectType;

public final class StatusEffectTypeTransformer implements Transformer<MobEffect, StatusEffectType> {

    protected StatusEffectTypeTransformer() {
    }

    @Override
    @NotNull
    public MobEffect toMinecraft(@NotNull StatusEffectType effectType) {
        return Registry.MOB_EFFECT.get(Transformer.NAMESPACED_KEY.toMinecraft(effectType.getKey()));
    }

    @Override
    @NotNull
    public StatusEffectType toLoom(@NotNull MobEffect mobEffect) {
        return StatusEffectType.getById(Registry.MOB_EFFECT.getKey(mobEffect).toString());
    }
}
