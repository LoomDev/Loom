package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.animal.Parrot;
import org.loomdev.loom.entity.TameableAnimalImpl;

public class ParrotImpl extends TameableAnimalImpl implements Parrot {

    public ParrotImpl(net.minecraft.world.entity.animal.Parrot entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Parrot> getType() {
        return EntityType.PARROT;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Parrot getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Parrot) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public Variant getVariant() {
        return Variant.values()[getMinecraftEntity().getVariant()];
    }

    @Override
    public void setVariant(@NotNull Variant variant) {
        getMinecraftEntity().setVariant(variant.ordinal());
    }
}
