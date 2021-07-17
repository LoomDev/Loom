package org.loomdev.loom.transformer;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a Minecraft to Loom (and vice versa) object converter.
 *
 * @param <M> The Minecraft object.
 * @param <L> The Loom variant of the object.
 */
public interface Transformer<M, L> {

    ComponentTransformer COMPONENT = new ComponentTransformer();
    DamageSourceTransformer DAMAGE_SOURCE = new DamageSourceTransformer();
    EulerAngleTransformer EULER_ANGLE = new EulerAngleTransformer();
    NamespacedKeyTransformer NAMESPACED_KEY = new NamespacedKeyTransformer();
    ParticleTransformer PARTICLE = new ParticleTransformer();
    ResourcePackStatusTransformer RESOURCE_PACK_STATUS = new ResourcePackStatusTransformer();
    StatusEffectTransformer STATUS_EFFECT = new StatusEffectTransformer();
    StatusEffectTypeTransformer STATUS_EFFECT_TYPE = new StatusEffectTypeTransformer();

    @NotNull
    M toMinecraft(@NotNull L loomObject);

    @NotNull
    L toLoom(@NotNull M minecraftObject);
}
