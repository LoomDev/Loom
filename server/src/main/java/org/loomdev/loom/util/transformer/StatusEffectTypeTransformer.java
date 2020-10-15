package org.loomdev.loom.util.transformer;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.effect.StatusEffectType;

public final class StatusEffectTypeTransformer {

    private StatusEffectTypeTransformer() {
        throw new UnsupportedOperationException("StatusEffectTypeTransformer shouldn't be initialized.");
    }

    public static @NotNull net.minecraft.entity.effect.StatusEffect toMinecraft(@NotNull StatusEffectType statusEffectType) {
        return Registry.STATUS_EFFECT.get(new Identifier(statusEffectType.getKey().toString()));
    }

    public static @NotNull StatusEffectType toLoom(@NotNull net.minecraft.entity.effect.StatusEffect statusEffect) {
        return StatusEffectType.getById(Registry.STATUS_EFFECT.getId(statusEffect).toString());
    }
}
