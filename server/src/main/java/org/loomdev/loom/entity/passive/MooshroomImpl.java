package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.sound.SoundCategory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.effect.StatusEffect;
import org.loomdev.api.entity.passive.Mooshroom;
import org.loomdev.loom.util.transformer.StatusEffectTypeTransformer;

public class MooshroomImpl extends CowImpl implements Mooshroom {

    public MooshroomImpl(MooshroomEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.MOOSHROOM;
    }

    @Override
    public @NotNull MooshroomEntity getMinecraftEntity() {
        return (MooshroomEntity) super.getMinecraftEntity();
    }

    @Override
    public @Nullable StatusEffect getStewEffect() {
        if (getMinecraftEntity().stewEffect == null) {
            return null;
        }

        return StatusEffect.builder(StatusEffectTypeTransformer.toLoom(getMinecraftEntity().stewEffect))
                .duration(getMinecraftEntity().stewEffectDuration)
                .build();
    }

    @Override
    public void setStewEffect(@Nullable StatusEffect statusEffect) {
        if (statusEffect == null) {
            getMinecraftEntity().stewEffect = null;
            getMinecraftEntity().stewEffectDuration = 0;
            return;
        }

        getMinecraftEntity().stewEffect = StatusEffectTypeTransformer.toMinecraft(statusEffect.getType());
        getMinecraftEntity().stewEffectDuration = statusEffect.getDuration();
    }

    @Override
    public @NotNull Variant getVariant() {
        return Variant.values()[getMinecraftEntity().getMooshroomType().ordinal()];
    }

    @Override
    public void setVariant(@NotNull Variant variant) {
        MooshroomEntity.Type type = MooshroomEntity.Type.values()[variant.ordinal()];
        getMinecraftEntity().dataTracker.set(MooshroomEntity.TYPE, type.name);
    }

    @Override
    public boolean isShearable() {
        return getMinecraftEntity().isShearable();
    }

    @Override
    public void shear() {
        getMinecraftEntity().sheared(SoundCategory.NEUTRAL);
    }
}
