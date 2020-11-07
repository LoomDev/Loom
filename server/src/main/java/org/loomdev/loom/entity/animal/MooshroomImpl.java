package org.loomdev.loom.entity.animal;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.animal.MushroomCow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.effect.StatusEffect;
import org.loomdev.api.entity.animal.Mooshroom;
import org.loomdev.loom.util.transformer.StatusEffectTypeTransformer;

public class MooshroomImpl extends CowImpl implements Mooshroom {

    public MooshroomImpl(MushroomCow entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Mooshroom> getType() {
        return EntityType.MOOSHROOM;
    }

    @Override
    @NotNull
    public MushroomCow getMinecraftEntity() {
        return (MushroomCow) super.getMinecraftEntity();
    }

    @Override
    @Nullable
    public StatusEffect getStewEffect() {
        if (getMinecraftEntity().effect == null) {
            return null;
        }

        return StatusEffect.builder(StatusEffectTypeTransformer.toLoom(getMinecraftEntity().effect))
                .duration(getMinecraftEntity().effectDuration)
                .build();
    }

    @Override
    public void setStewEffect(@Nullable StatusEffect statusEffect) {
        if (statusEffect == null) {
            getMinecraftEntity().effect = null;
            getMinecraftEntity().effectDuration = 0;
            return;
        }

        getMinecraftEntity().effect = StatusEffectTypeTransformer.toMinecraft(statusEffect.getType());
        getMinecraftEntity().effectDuration = statusEffect.getDuration();
    }

    @Override
    @NotNull
    public Variant getVariant() {
        return Variant.values()[getMinecraftEntity().getMushroomType().ordinal()];
    }

    @Override
    public void setVariant(@NotNull Variant variant) {
        var type = MushroomCow.MushroomType.values()[variant.ordinal()];
        getMinecraftEntity().entityData.set(MushroomCow.DATA_TYPE, type.type);
    }

    @Override
    public boolean isShearable() {
        return getMinecraftEntity().readyForShearing();
    }

    @Override
    public void shear() {
        getMinecraftEntity().shear(SoundSource.NEUTRAL);
    }
}
