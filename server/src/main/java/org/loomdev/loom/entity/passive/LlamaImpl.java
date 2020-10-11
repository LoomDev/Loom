package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.LlamaEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Llama;
import org.loomdev.api.math.MathHelper;

public class LlamaImpl extends AbstractDonkeyImpl implements Llama {

    public LlamaImpl(LlamaEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.LLAMA;
    }

    @Override
    public @NotNull LlamaEntity getMinecraftEntity() {
        return (LlamaEntity) super.getMinecraftEntity();
    }

    @Override
    public int getStrength() {
        return getMinecraftEntity().getStrength();
    }

    @Override
    public void setStrength(int strength) {
        getMinecraftEntity().dataTracker.set(LlamaEntity.STRENGTH, MathHelper.clamp(strength, 1, 5));
    }

    @Override
    public @NotNull Variant getVariant() {
        return Variant.values()[getMinecraftEntity().getVariant()];
    }

    @Override
    public void setVariant(Variant variant) {
        getMinecraftEntity().setVariant(variant.ordinal());
    }
}
