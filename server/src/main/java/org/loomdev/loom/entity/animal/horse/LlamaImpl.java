package org.loomdev.loom.entity.animal.horse;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.animal.horse.Llama;
import org.loomdev.api.math.MathHelper;
public class LlamaImpl extends AbstractChestedHorseImpl implements Llama {

    public LlamaImpl(net.minecraft.world.entity.animal.horse.Llama entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<? extends Llama> getType() {
        return EntityType.LLAMA;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.horse.Llama getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.horse.Llama) super.getMinecraftEntity();
    }

    @Override
    public int getStrength() {
        return getMinecraftEntity().getStrength();
    }

    @Override
    public void setStrength(int strength) {
        getMinecraftEntity().entityData.set(net.minecraft.world.entity.animal.horse.Llama.DATA_STRENGTH_ID, MathHelper.clamp(strength, 1, 5));
    }

    @Override
    @NotNull
    public Variant getVariant() {
        return Variant.values()[getMinecraftEntity().getVariant()];
    }

    @Override
    public void setVariant(Variant variant) {
        getMinecraftEntity().setVariant(variant.ordinal());
    }
}
