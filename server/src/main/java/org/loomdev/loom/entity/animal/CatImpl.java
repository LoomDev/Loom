package org.loomdev.loom.entity.animal;

import org.jetbrains.annotations.NotNull;
import org.loomdev.api.entity.EntityType;
import org.loomdev.api.entity.animal.Cat;
import org.loomdev.api.util.DyeColor;
import org.loomdev.loom.entity.TameableAnimalImpl;

public class CatImpl extends TameableAnimalImpl implements Cat {

    public CatImpl(net.minecraft.world.entity.animal.Cat entity) {
        super(entity);
    }

    @Override
    @NotNull
    public EntityType<Cat> getType() {
        return EntityType.CAT;
    }

    @Override
    @NotNull
    public net.minecraft.world.entity.animal.Cat getMinecraftEntity() {
        return (net.minecraft.world.entity.animal.Cat) super.getMinecraftEntity();
    }

    @Override
    @NotNull
    public Variant getVariant() {
        return Variant.values()[getMinecraftEntity().getCatType()];
    }

    @Override
    public void setVariant(Variant type) {
        getMinecraftEntity().setCatType(type.ordinal());
    }

    @Override
    @NotNull
    public DyeColor getCollarColor() {
        return DyeColor.getById(getMinecraftEntity().getCollarColor().getId());
    }

    @Override
    public void setCollarColor(DyeColor color) {
        getMinecraftEntity().setCollarColor(net.minecraft.world.item.DyeColor.byId(color.getId()));
    }

    @Override
    public void hiss() {
        getMinecraftEntity().hiss();
    }

    @Override
    public boolean isSitting() {
        return getMinecraftEntity().isInSittingPose();
    }

    @Override
    public void setSitting(boolean flag) {
        getMinecraftEntity().setInSittingPose(flag);
    }
}
