package org.loomdev.loom.util.transformer;

import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.effect.StatusEffect;

public final class StatusEffectTypeTransformer {
    private StatusEffectTypeTransformer() { throw new UnsupportedOperationException("StatusEffectTypeTransformer shouldn't be initialized."); }

    public static @NotNull net.minecraft.entity.effect.StatusEffect toMinecraft(@NotNull StatusEffect.Type statusEffectType) {
        return Registry.STATUS_EFFECT.get(statusEffectType.rawId());
    }

    public static @NotNull StatusEffect.Type toLoom(@NotNull net.minecraft.entity.effect.StatusEffect statusEffect) {
        return StatusEffect.Type.getByRawId(Registry.STATUS_EFFECT.getRawId(statusEffect));
    }
}
