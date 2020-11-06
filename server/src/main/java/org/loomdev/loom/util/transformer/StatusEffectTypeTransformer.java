package org.loomdev.loom.util.transformer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.effect.StatusEffectType;

public final class StatusEffectTypeTransformer {

    private StatusEffectTypeTransformer() {
    }

    @NotNull
    public static MobEffect toMinecraft(@NotNull StatusEffectType statusEffectType) {
        return net.minecraft.core.Registry.MOB_EFFECT.get(new ResourceLocation(statusEffectType.getKey().toString()));
    }

    @NotNull
    public static StatusEffectType toLoom(@NotNull MobEffect statusEffect) {
        return StatusEffectType.getById(net.minecraft.core.Registry.MOB_EFFECT.getKey(statusEffect).toString()); // TODO ???
    }
}
