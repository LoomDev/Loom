package org.loomdev.loom.util.transformer;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.registry.Registry;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.loomdev.api.entity.effect.StatusEffectType;

public final class StatusEffectTypeTransformer {
    private StatusEffectTypeTransformer() { throw new UnsupportedOperationException("StatusEffectTypeTransformer shouldn't be initialized."); }

    public static @NonNull StatusEffect toMinecraft(@NonNull StatusEffectType statusEffectType) {
        return Registry.STATUS_EFFECT.get(statusEffectType.rawId());
    }

    public static @NonNull StatusEffectType toLoom(@NonNull StatusEffect statusEffect) {
        return StatusEffectType.getByRawId(Registry.STATUS_EFFECT.getRawId(statusEffect));
    }
}
