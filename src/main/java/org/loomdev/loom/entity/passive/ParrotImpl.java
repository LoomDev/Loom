package org.loomdev.loom.entity.passive;

import net.minecraft.entity.passive.ParrotEntity;
import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.passive.Parrot;

public class ParrotImpl extends TameableEntityImpl implements Parrot {

    public ParrotImpl(ParrotEntity entity) {
        super(entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.PARROT;
    }

    @Override
    public @NotNull ParrotEntity getMinecraftEntity() {
        return (ParrotEntity) super.getMinecraftEntity();
    }

    @Override
    public @NotNull Variant getVariant() {
        return Variant.values()[getMinecraftEntity().getVariant()];
    }

    @Override
    public void setVariant(@NotNull Variant variant) {
        getMinecraftEntity().setVariant(variant.ordinal());
    }
}
